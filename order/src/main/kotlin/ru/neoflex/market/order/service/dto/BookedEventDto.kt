package ru.neoflex.market.order.service.dto

import java.util.*

data class BookedEventDto(
    val userId: UUID,
    val orderId: UUID
)
