package ru.neoflex.market.api.gateway.dto

import java.util.*

data class ProductRequest(
    val productId: UUID,
    val userId: UUID
)
