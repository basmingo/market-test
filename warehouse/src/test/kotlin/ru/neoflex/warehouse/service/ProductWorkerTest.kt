package ru.neoflex.warehouse.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass
import java.util.*

@SpringBootTest
class ProductWorkerTest {

    @Autowired
    lateinit var productWorker: ProductWorker

    @Autowired
    lateinit var warehouseServiceImpl: WarehouseServiceImpl

    @Test
    fun toDeliveryProductWorker() {
        runBlocking {
            val response: List<String> = (1..5)
                .map {
                    warehouseServiceImpl.createProduct(
                        WarehouseServiceOuterClass.ProductCreateRequest
                            .newBuilder()
                            .apply { displayName = "PRODUCT $it" }
                            .build()
                    )
                }
                .map { it.productId }

            productWorker.toDeliveryProduct(response)

            val resultStatuses: List<String?> = response
                .map { UUID.fromString(it) }
                .map { warehouseServiceImpl.getById(it) }
                .map { it?.status }

            assert(resultStatuses.all { it == "TO_DELIVERY" })
        }
    }

    @Test
    fun onAvailableProductWorker() {
        runBlocking {
            val response: List<String> = (1..5)
                .map {
                    warehouseServiceImpl.createProduct(
                        WarehouseServiceOuterClass.ProductCreateRequest
                            .newBuilder()
                            .apply { displayName = "PRODUCT $it" }
                            .build()
                    )
                }
                .map { it.productId }

            productWorker.toDeliveryProduct(response)
            productWorker.onAvailableProduct(response)

            val resultStatuses: List<String?> = response
                .map { UUID.fromString(it) }
                .map { warehouseServiceImpl.getById(it) }
                .map { it?.status }

            assert(resultStatuses.all { it == "AVAILABLE" })
        }
    }
}