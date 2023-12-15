package ru.neoflex.market.api.gateway.dto

import java.math.BigDecimal

data class BalanceUpRequestDto(
    val userId: String,
    val amount: String
)
