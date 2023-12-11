package ru.neoflex.market.order.service

import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.kafka.annotation.KafkaListener
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.OrderServiceGrpcKt
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse
import ru.neoflex.market.order.repository.OrderDao
import ru.neoflex.market.order.repository.ProductDao
import ru.neoflex.market.order.service.dto.OrderDto
import ru.neoflex.market.order.service.dto.ProductDto
import ru.neoflex.market.order.service.dto.UnbookedEventDto
import java.time.LocalDateTime
import java.util.*

@GrpcService
class OrderServiceImpl(private val productDao: ProductDao, private val orderDao: OrderDao) :
    OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun getOrder(request: OrderRequest): OrderResponse {
        println(request)
        println(request.userId)
        val uid = UUID.fromString(request.userId)
        println("UID = $uid")
        val order = orderDao.getByUserId(uid)
        println(order)

        return OrderResponse
            .newBuilder()
            .apply { orderId = "$order?.orderId" }
            .apply { status = "STATUS_TEST" }
            .build()
    }

    fun getOrderTest(userId: UUID): OrderDto =
        orderDao.getByUserId(userId)

    fun insertOrder(order: OrderDto) =
        orderDao.create(order)

    @KafkaListener(
        topics = ["product_booked_event"],
        groupId = "order",
        containerFactory = "productBookedEventContainerListener"
    )
    fun handleProductBookedEvent(message: BookedEventDto) {
        println(message)
        productDao.insert(ProductDto(message.productId, LocalDateTime.now(), message.orderId))
    }

    @KafkaListener(
        topics = ["product_unbooked_event"],
        groupId = "order",
        containerFactory = "productUnbookedEventContainerListener"
    )
    fun handleProductUnbookedEvent(message: UnbookedEventDto) {
        println(message)
        productDao.delete(message.productIds)
    }
}