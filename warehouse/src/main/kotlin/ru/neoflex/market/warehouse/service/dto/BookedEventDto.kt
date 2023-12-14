package ru.neoflex.market.warehouse.service.dto

import java.util.*

data class BookedEventDto(
    val userId: UUID,
    val productId: UUID
)
