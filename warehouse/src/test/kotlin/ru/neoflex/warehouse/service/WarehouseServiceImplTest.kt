package ru.neoflex.warehouse.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass.*
import java.util.*

@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    lateinit var warehouseServiceImpl: WarehouseServiceImpl

    @RepeatedTest(100)
    fun createProductTest() {
        runBlocking {
            val testDisplayName = "TEST_PRODUCT"
            val response = warehouseServiceImpl.createProduct(
                ProductCreateRequest
                    .newBuilder()
                    .apply { displayName = testDisplayName }
                    .build()
            )
            val productId = UUID.fromString(response.productId)
            val product = warehouseServiceImpl.getById(productId)

            assert(
                product?.productId == productId
                        && product?.displayName == testDisplayName
                        && product.status == "AVAILABLE"
            )
        }
    }

    @Test
    fun updateProductTest() {
        runBlocking {
            val createReponse: ProductCreateResponse = warehouseServiceImpl.createProduct(
                ProductCreateRequest
                    .newBuilder()
                    .apply { displayName = "TEST_PRODUCT" }
                    .build()
            )

            val responseProductId = createReponse.productId
            warehouseServiceImpl.bookProduct(
                ProductBookRequest
                    .newBuilder()
                    .apply { productId = responseProductId }
                    .apply { userId = "${UUID.randomUUID()}" }
                    .build()
            )

            val updatedResponse = warehouseServiceImpl.getById(UUID.fromString(createReponse.productId))
            assert(updatedResponse?.status == "BOOKED")
        }
    }

    @Test
    fun deleteProductTest() {
        runBlocking {
            val createReponse: ProductCreateResponse = warehouseServiceImpl.createProduct(
                ProductCreateRequest
                    .newBuilder()
                    .apply { displayName = "TEST_PRODUCT" }
                    .build()
            )
            warehouseServiceImpl.deleteProduct(
                ProductDeleteRequest
                    .newBuilder()
                    .apply { productId = createReponse.productId }
                    .build()
            )

            val updatedResponse = warehouseServiceImpl.getById(UUID.fromString(createReponse.productId))
            assert(updatedResponse == null)
        }
    }
}