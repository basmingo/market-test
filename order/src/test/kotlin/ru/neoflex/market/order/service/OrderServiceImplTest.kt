package ru.neoflex.market.order.service

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.service.dto.OrderDto
import ru.neoflex.market.order.service.dto.UnbookedEventDto
import java.util.*

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    lateinit var orderServiceImpl: OrderServiceImpl

    @Test
    fun bookProductTest() {
        val orderId: UUID = UUID.randomUUID()

        for (i in 1..5) {
            orderServiceImpl.handleProductBookedEvent(
                BookedEventDto(
                    userId = orderId,
                    productId = UUID.randomUUID()
                )
            )
        }
    }

    @Test
    fun unbookProductsTest() {
        val orderId: UUID = UUID.randomUUID()
        val productIds: List<UUID> = (1..5).map { UUID.randomUUID() }

        for (productId in productIds) {
            orderServiceImpl.handleProductBookedEvent(
                BookedEventDto(
                    userId = orderId,
                    productId = productId
                )
            )
        }

        orderServiceImpl.handleProductUnbookedEvent(UnbookedEventDto(productIds))
    }

    @Test
    fun unbookOneProductTest() {
        val productId: UUID = UUID.randomUUID()

        orderServiceImpl.handleProductBookedEvent(
            BookedEventDto(
                userId = UUID.randomUUID(),
                productId = productId
            )
        )

        orderServiceImpl.handleProductUnbookedEvent(
            UnbookedEventDto(
                productIds = listOf(productId)
            )
        )
    }

    @Test
    fun getOrderTest() {
        val userId = UUID.randomUUID()
        val orderId = UUID.randomUUID()

        orderServiceImpl.insertOrder(
            OrderDto(
                orderId = orderId,
                userId = userId,
                status = "STATUS_TEST"
            )
        )

        val i = orderServiceImpl.getOrderByUserId(userId)
        assert(i == OrderDto(orderId, userId, "STATUS_TEST"))
    }

    @Test
    fun updateOrderTest() {
        val orderId = UUID.randomUUID()
        val userId = UUID.randomUUID()

        orderServiceImpl.insertOrder(
            OrderDto(
                orderId = orderId,
                userId = userId,
                status = "STATUS_TEST"
            )
        )

        orderServiceImpl.updateOrderStatus(
            status = "NEW",
            orderId = orderId
        )

        val result = orderServiceImpl.getOrderByUserId(userId)
        println(result)
        assert(
            result == OrderDto(
                orderId = orderId,
                userId = userId,
                status = "NEW"
            )
        )
    }
}