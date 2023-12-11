package ru.neoflex.market.order.service.dto

import java.util.*

data class OrderDto(
    val orderId: UUID,
    val userId: UUID,
    val status: String
)
