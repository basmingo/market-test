package ru.neoflex.market.order.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.neoflex.market.order.repository.OrderDao
import ru.neoflex.market.order.repository.ProductDao
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.service.dto.OrderDto
import ru.neoflex.market.order.service.dto.ProductDto
import ru.neoflex.market.order.service.dto.UnbookedEventDto
import java.time.LocalDateTime
import java.util.*

@Service
class ProductService(private val productDao: ProductDao, private val orderDao: OrderDao) {

    @KafkaListener(
        topics = ["product_booked_event"],
        groupId = "order",
        containerFactory = "productBookedEventContainerListener"
    )
    fun handleProductBookedEvent(message: BookedEventDto) {
        println(message)
        val orderId: UUID? = orderDao.getByUserId(message.userId)?.orderId
        val possibleOrderId = UUID.randomUUID()

        orderId ?: orderDao.create(
            OrderDto(possibleOrderId, message.userId, "CREATED")
        )
        productDao.insert(ProductDto(message.productId, LocalDateTime.now(), orderId ?: possibleOrderId))
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