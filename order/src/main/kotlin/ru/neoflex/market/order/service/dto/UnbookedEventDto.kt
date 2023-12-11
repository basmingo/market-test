package ru.neoflex.market.order.service.dto

import java.util.*

data class UnbookedEventDto(
    val userId: UUID,
    val orderId: UUID
)
