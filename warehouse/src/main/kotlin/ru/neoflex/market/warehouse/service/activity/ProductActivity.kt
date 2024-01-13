package ru.neoflex.market.warehouse.service.activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.util.UUID

@ActivityInterface
interface ProductActivity {

    @ActivityMethod
    fun toDeliveryProduct(productIds: List<UUID>)

    @ActivityMethod
    fun onAvailableProduct(productIds: List<UUID>)
}