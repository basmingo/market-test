package ru.neoflex.market.order.service.dto

import java.time.LocalDateTime
import java.util.*

data class ProductDto(val productId: UUID, val created: LocalDateTime, val orderId: UUID)
