package ru.neoflex.market.warehouse.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass
import java.util.*

@SpringBootTest
class ProductActivityImplTest {

    @Autowired
    lateinit var productActivityImpl: ProductActivityImpl

    @Autowired
    lateinit var warehouseServiceImpl: WarehouseServiceImpl

    @Test
    fun toDeliveryProductWorker() {
        runBlocking {
            val response: List<UUID> = (1..5)
                .map {
                    warehouseServiceImpl.createProduct(
                        WarehouseServiceOuterClass.ProductCreateRequest
                            .newBuilder()
                            .apply { displayName = "PRODUCT $it" }
                            .build()
                    )
                }
                .map { UUID.fromString(it.productId) }

            productActivityImpl.toDeliveryProduct(response)

            val resultStatuses: List<String?> = response
                .map { it }
                .map { warehouseServiceImpl.getById(it) }
                .map { it?.status }

            assert(resultStatuses.all { it == "TO_DELIVERY" })
        }
    }

    @Test
    fun onAvailableProductWorker() {
        runBlocking {
            val response: List<UUID> = (1..5)
                .map {
                    warehouseServiceImpl.createProduct(
                        WarehouseServiceOuterClass.ProductCreateRequest
                            .newBuilder()
                            .apply { displayName = "PRODUCT $it" }
                            .build()
                    )
                }
                .map { UUID.fromString(it.productId) }

            productActivityImpl.toDeliveryProduct(response)
            productActivityImpl.onAvailableProduct(response)

            val resultStatuses: List<String?> = response
                .map { it }
                .map { warehouseServiceImpl.getById(it) }
                .map { it?.status }

            assert(resultStatuses.all { it == "AVAILABLE" })
        }
    }
}