package ru.neoflex.market.api.gateway.dto

import java.util.*

data class OrderResponseDto(
    val orderId: UUID,
    val status: String
)
