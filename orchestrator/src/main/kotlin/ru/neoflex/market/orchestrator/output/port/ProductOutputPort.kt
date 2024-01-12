package org.example.ru.neoflex.market.orchestrator.output.port

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.util.UUID

@ActivityInterface
interface ProductOutputPort {

    @ActivityMethod
    fun toDeliveryProduct(productIds: List<UUID>)

    @ActivityMethod
    fun onAvailableProduct(productIds: List<UUID>)
}