package ru.neoflex.market.user.service.dto

import java.math.BigDecimal
import java.util.UUID

data class UserDto(
    val userId: UUID,
    val name: String,
    val lastName: String,
    val balance: BigDecimal,
    val age: Int
)