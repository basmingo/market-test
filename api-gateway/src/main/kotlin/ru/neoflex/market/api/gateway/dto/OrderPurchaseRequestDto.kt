package ru.neoflex.market.api.gateway.dto

import java.math.BigDecimal
import java.util.UUID

data class OrderPurchaseRequestDto(val userId: UUID, val amount: BigDecimal)
