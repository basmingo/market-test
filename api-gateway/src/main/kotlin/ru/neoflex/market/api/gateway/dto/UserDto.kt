package ru.neoflex.market.api.gateway.dto

import java.math.BigDecimal
import java.util.*

data class UserDto(
    val userId: UUID,
    val name: String,
    val lastName: String,
    val balance: BigDecimal,
    val age: Int
)
