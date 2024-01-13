package ru.neoflex.market.order.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.service.dto.OrderDetailsResponse
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@SpringBootTest
class OrderActivityImplTest {

    @Autowired
    lateinit var orderActivity: OrderActivityImpl

    @Autowired
    lateinit var orderServiceImpl: OrderServiceImpl

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun getOrderDetailsWorker() {
        runBlocking {
            val testUserId = UUID.randomUUID()
            val testOrderId = UUID.randomUUID()

            orderServiceImpl.insertOrder(
                OrderDto(
                    orderId = testOrderId,
                    userId = testUserId,
                    status = "NEW"
                )
            )

            val productIds: List<UUID> = (1..5)
                .map { UUID.randomUUID() }

            productIds.forEach {
                productService.handleProductBookedEvent(
                    BookedEventDto(
                        userId = testUserId,
                        productId = it
                    )
                )
            }

            val details = orderActivity.getOrderDetails(testUserId)
            assert(details == OrderDetailsResponse(testOrderId, productIds))
        }
    }
}