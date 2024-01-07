package ru.neoflex.market.api.gateway.dto

import java.util.*

data class ProductDto(
    val productId: UUID,
    val displayName: String,
    val created: String,
    val updated: String,
    val status: String
)
