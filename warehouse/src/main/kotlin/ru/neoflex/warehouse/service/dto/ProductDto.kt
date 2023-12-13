package ru.neoflex.warehouse.service.dto

import java.time.LocalDateTime
import java.util.*

data class ProductDto(
    val productId: UUID,
    val displayName: String,
    val created: LocalDateTime,
    val updated: LocalDateTime,
    val status: String
)
