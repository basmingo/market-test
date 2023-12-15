package ru.neoflex.market.api.gateway.dto

data class UserCreateDto(
    val name: String,
    val lastName: String,
    val balance: String,
    val age: Int
)
