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
                    orderId,
                    UUID.randomUUID(),
                    UUID.randomUUID()
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
                BookedEventDto(orderId, UUID.randomUUID(), productId)
            )
        }

        orderServiceImpl.handleProductUnbookedEvent(UnbookedEventDto(productIds))
    }

    @Test
    fun unbookOneProductTest() {
        val productId: UUID = UUID.randomUUID()

        orderServiceImpl.handleProductBookedEvent(
            BookedEventDto(UUID.randomUUID(), UUID.randomUUID(), productId)
        )

        orderServiceImpl.handleProductUnbookedEvent(
            UnbookedEventDto(
                listOf(productId)
            )
        )
    }

    @Test
    fun getOrderTest() {
        val userId = UUID.randomUUID()
        val orderId = UUID.randomUUID()
        orderServiceImpl.insertOrder(
            OrderDto(orderId, userId, "STATUS_TEST")
        )

        val i = orderServiceImpl.getOrderByUserId(userId)
        assert(i == OrderDto(orderId, userId, "STATUS_TEST"))
    }
}