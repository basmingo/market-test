package org.example.ru.neoflex.market.orchestrator.output.port

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import org.example.ru.neoflex.market.orchestrator.output.port.dto.OrderDetailsResponse
import java.util.*

@ActivityInterface
interface OrderOutputPort {
    @ActivityMethod
    fun getOrderDetails(userId: UUID): OrderDetailsResponse

    @ActivityMethod
    fun setStatusToDelivery(orderId: UUID)
}