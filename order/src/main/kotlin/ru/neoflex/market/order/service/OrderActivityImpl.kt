package ru.neoflex.market.order.service

import org.springframework.stereotype.Service
import ru.neoflex.market.order.repository.OrderDao
import ru.neoflex.market.order.repository.ProductDao
import ru.neoflex.market.order.service.activity.OrderActivity
import ru.neoflex.market.order.service.dto.OrderDetailsResponse
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@Service
class OrderActivityImpl(private val orderDao: OrderDao, private val productDao: ProductDao) : OrderActivity {

    override fun setStatusToDelivery(orderId: UUID) {
        orderDao.update("TO_DELIVERY", orderId)
    }

    override fun getOrderDetails(userId: UUID): OrderDetailsResponse {
        val order: OrderDto = orderDao.getByUserId(userId) ?: throw RuntimeException("No orders found")
        val products: List<UUID> = productDao
            .getByOrderId(orderId = order.orderId)
            .map { it.productId }

        return OrderDetailsResponse(order.orderId, products)
    }
}