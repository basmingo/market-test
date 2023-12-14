package ru.neoflex.market.order.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderRequest
import ru.neoflex.market.order.OrderServiceOuterClass.OrderResponse
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    lateinit var orderServiceImpl: OrderServiceImpl

    @Test
    fun getOrderTest() {
        runBlocking {
            val testUserId = UUID.randomUUID()
            val testOrderId = UUID.randomUUID()

            orderServiceImpl.insertOrder(
                OrderDto(
                    orderId = testOrderId,
                    userId = testUserId,
                    status = "STATUS_TEST"
                )
            )

            val returnedOrder = orderServiceImpl.getOrder(
                OrderRequest
                    .newBuilder()
                    .apply { userId = "$testUserId" }
                    .build()
            )
            assert(returnedOrder == OrderResponse.newBuilder()
                .apply { orderId = "$testOrderId" }
                .apply { status = "STATUS_TEST" }
                .build())
        }
    }

    @Test
    fun updateOrderTest() {
        runBlocking {
            val testOrderId = UUID.randomUUID()
            val testUserId = UUID.randomUUID()

            orderServiceImpl.insertOrder(
                OrderDto(
                    orderId = testOrderId,
                    userId = testUserId,
                    status = "STATUS_TEST"
                )
            )

            orderServiceImpl.updateOrderStatus(
                status = "NEW",
                orderId = testOrderId
            )

            val returnedOrder = orderServiceImpl.getOrder(
                OrderRequest
                    .newBuilder()
                    .apply { userId = "$testUserId" }
                    .build()
            )

            assert(returnedOrder == OrderResponse.newBuilder()
                .apply { orderId = "$testOrderId" }
                .apply { status = "NEW" }
                .build())
        }
    }
}