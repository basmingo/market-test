package ru.neoflex.market.order.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.service.dto.UnbookedEventDto
import java.util.*

@SpringBootTest
class ProductServiceTest {

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun bookProductTest() {
        val testUserId: UUID = UUID.randomUUID()

        for (i in 1..5) {
            productService.handleProductBookedEvent(
                BookedEventDto(
                    userId = testUserId,
                    productId = UUID.randomUUID()
                )
            )
        }
    }

    @Test
    fun unbookProductsTest() {
        val testUserId: UUID = UUID.randomUUID()
        val productIds: List<UUID> = (1..5).map { UUID.randomUUID() }

        for (productId in productIds) {
            productService.handleProductBookedEvent(
                BookedEventDto(
                    userId = testUserId,
                    productId = productId
                )
            )
        }

        productService.handleProductUnbookedEvent(UnbookedEventDto(productIds))
    }

    @Test
    fun unbookOneProductTest() {
        val productId: UUID = UUID.randomUUID()

        productService.handleProductBookedEvent(
            BookedEventDto(
                userId = UUID.randomUUID(),
                productId = productId
            )
        )

        productService.handleProductUnbookedEvent(
            UnbookedEventDto(
                productIds = listOf(productId)
            )
        )
    }

}