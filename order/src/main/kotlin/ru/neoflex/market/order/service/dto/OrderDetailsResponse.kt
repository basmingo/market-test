package ru.neoflex.market.order.service.dto

import java.util.*

data class OrderDetailsResponse(val orderId: UUID, val productIds: List<UUID>)
