package ru.neoflex.warehouse.service

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import org.springframework.stereotype.Service
import ru.neoflex.warehouse.repository.ProductDao
import java.util.*

@Service
class ProductWorker(private val productDao: ProductDao) {

    @JobWorker(type = "setProductsStatusToDelivery")
    fun toDeliveryProduct(@Variable productIds: List<String>) {
        productDao.update(productIds.map { UUID.fromString(it) }, "TO_DELIVERY")
    }

    @JobWorker(type = "setProductsStatusOnAvailable")
    fun onAvailableProduct(@Variable productIds: List<String>) {
        productDao.update(productIds.map { UUID.fromString(it) }, "AVAILABLE")
    }
}