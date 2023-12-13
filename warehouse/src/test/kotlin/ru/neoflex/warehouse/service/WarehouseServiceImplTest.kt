package ru.neoflex.warehouse.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.warehouse.WarehouseServiceOuterClass.ProductCreateRequest

@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    lateinit var warehouseServiceImpl: WarehouseServiceImpl

    @Test
    fun createProductTest() {
        runBlocking {
            warehouseServiceImpl.createProduct(
                ProductCreateRequest
                    .newBuilder()
                    .apply { displayName = "TEST_PRODUCT" }
                    .build()
            )
        }
    }

    @Test
    fun updateProductTest() {
    }

    @Test
    fun deleteProductTest() {
    }
}