package ru.neoflex.market.warehouse.service

import org.springframework.stereotype.Service
import ru.neoflex.market.warehouse.repository.ProductDao
import ru.neoflex.market.warehouse.service.activity.ProductActivity
import java.util.*

@Service
class ProductActivityImpl(private val productDao: ProductDao) : ProductActivity {

    override fun toDeliveryProduct(productIds: List<UUID>) {
        productDao.update(productIds.map { it }, "TO_DELIVERY")
    }

    override fun onAvailableProduct(productIds: List<UUID>) {
        productDao.update(productIds.map { it }, "AVAILABLE")
    }
}