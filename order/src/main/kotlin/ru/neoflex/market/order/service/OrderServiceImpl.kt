package ru.neoflex.market.order.service

import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.kafka.annotation.KafkaListener
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.OrderServiceGrpcKt
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse
import ru.neoflex.market.order.service.dto.UnbookedEventDto

@GrpcService
class OrderServiceImpl : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun getOrder(request: OrderRequest): OrderResponse {
        return OrderResponse
            .newBuilder()
            .apply { response = "${request.id} RESPONSE" }
            .build()
    }

    @KafkaListener(
        topics = ["product_booked_event"],
        groupId = "order",
        containerFactory = "productBookedEventContainerListener"
    )
    fun handleProductBookedEvent(message: BookedEventDto) {
        println(message)
    }

    @KafkaListener(
        topics = ["product_unbooked_event"],
        groupId = "order",
        containerFactory = "productUnbookedEventContainerListener"
    )
    fun handleProductUnbookedEvent(message: UnbookedEventDto) {
        println(message)
    }
}