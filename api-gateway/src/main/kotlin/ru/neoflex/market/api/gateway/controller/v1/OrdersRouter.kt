package ru.neoflex.market.api.gateway.controller.v1

import io.camunda.zeebe.client.ZeebeClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.neoflex.market.api.gateway.config.GrpcClient
import ru.neoflex.market.api.gateway.dto.OrderResponseDto
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import java.util.*

@RestController
@RequestMapping("/orders")
class OrdersRouter(private val grpcClient: GrpcClient,
                   private val zeebeClient: ZeebeClient) {

    @Value("\${order.bpmn.process.id}")
    lateinit var orderBpmnProcessId: String

    @GetMapping
    fun get(@RequestParam("id") userId: UUID): Mono<OrderResponseDto> =
        Mono.just(userId)
            .map {
                grpcClient
                    .getOrderServiceClient()
                    .getOrder(
                        OrderRequest
                            .newBuilder()
                            .apply { this.userId = "$userId" }
                            .build()
                    )
            }
            .map {
                OrderResponseDto(
                    UUID.fromString(it.orderId),
                    it.status
                )
            }

    @PostMapping("/purchase")
    fun purchase() {
        zeebeClient
            .newCreateInstanceCommand()
            .bpmnProcessId(orderBpmnProcessId)
            .latestVersion()
            .send()
            .join()
    }
}