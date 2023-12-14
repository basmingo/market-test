package ru.neoflex.market.order.service

import net.devh.boot.grpc.server.service.GrpcService
import ru.neoflex.market.order.OrderServiceGrpcKt
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse
import ru.neoflex.market.order.repository.OrderDao
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@GrpcService
class OrderServiceImpl(private val orderDao: OrderDao) :
    OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    override suspend fun getOrder(request: OrderRequest): OrderResponse {
        val order = orderDao.getByUserId(UUID.fromString(request.userId))
        return OrderResponse
            .newBuilder()
            .apply { orderId = "${order?.orderId}" }
            .apply { status = "${order?.status}" }
            .build()
    }

    fun insertOrder(order: OrderDto) =
        orderDao.create(order)

    fun updateOrderStatus(status: String, orderId: UUID) {
        orderDao.update(status, orderId)
    }
}