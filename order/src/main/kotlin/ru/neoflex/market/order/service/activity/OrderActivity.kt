package ru.neoflex.market.order.service.activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import ru.neoflex.market.order.service.dto.OrderDetailsResponse
import java.util.*

@ActivityInterface
interface OrderActivity {
    @ActivityMethod
    fun getOrderDetails(userId: UUID): OrderDetailsResponse

    @ActivityMethod
    fun setStatusToDelivery(orderId: UUID)
}