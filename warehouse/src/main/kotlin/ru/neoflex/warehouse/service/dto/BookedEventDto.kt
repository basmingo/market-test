package ru.neoflex.warehouse.service.dto

import java.util.*

data class BookedEventDto(
    val userId: UUID,
    val productId: UUID
)
