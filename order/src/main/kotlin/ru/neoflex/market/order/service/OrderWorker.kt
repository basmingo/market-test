package ru.neoflex.market.order.service

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import org.springframework.stereotype.Service
import ru.neoflex.market.order.repository.OrderDao
import ru.neoflex.market.order.repository.ProductDao
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@Service
class OrderWorker(private val orderDao: OrderDao, private val productDao: ProductDao) {

    @JobWorker(type = "setOrderStatusToDelivery")
    fun setStatusToDelivery(@Variable orderId: String) {
        orderDao.update("TO_DELIVERY", UUID.fromString(orderId))
    }

    @JobWorker(type = "getOrder")
    fun getOrderDetails(@Variable userId: String): Map<String, Any> {
        val order: OrderDto = orderDao.getByUserId(UUID.fromString(userId)) ?: throw RuntimeException("No orders found")
        val products: List<String> = productDao
            .getByOrderId(orderId = order.orderId)
            .map { "${it.productId}" }

        return mapOf(
            "orderId" to "${order.orderId}",
            "productIds" to products
        )
    }
}