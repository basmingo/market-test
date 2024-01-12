package ru.neoflex.market.api.gateway.controller.v1

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import ru.neoflex.market.api.gateway.config.GrpcClient
import ru.neoflex.market.api.gateway.dto.OrderPurchaseRequestDto
import ru.neoflex.market.api.gateway.dto.OrderResponseDto
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrhcestratorService.CommitOrderRequest
import java.util.*

@RestController
@RequestMapping("/orders")
class OrdersRouter(private val grpcClient: GrpcClient) {


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
    fun purchase(orderPurchaseDto: Mono<OrderPurchaseRequestDto>): Mono<Unit> {
        orderPurchaseDto
            .publishOn(Schedulers.parallel())
            .subscribe {
                grpcClient
                    .getOrchestratorServiceClient()
                    .commitOrder(
                        CommitOrderRequest
                            .newBuilder()
                            .apply {
                                this.userId = "${it.userId}"
                                this.amount = "${it.amount}"
                            }
                            .build()
                    )
            }

        return Mono.just(Unit)
    }
}