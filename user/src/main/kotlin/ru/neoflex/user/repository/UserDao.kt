package ru.neoflex.user.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.neoflex.user.service.dto.UserDto
import java.math.BigDecimal
import java.util.*

@Service
class UserDao(private val jdbcTemplate: JdbcTemplate) {

    fun create(user: UserDto) {
        jdbcTemplate
            .update(
                "INSERT INTO PUBLIC.\"USER\" VALUES (?, ?, ?, ?, ?)",
                user.userId,
                user.name,
                user.lastName,
                user.balance,
                user.age
            )
    }

    fun delete(userId: UUID) {
        jdbcTemplate
            .update(
                "DELETE FROM PUBLIC.\"USER\" WHERE U_ID = ?",
                userId
            )
    }

    fun plusBalance(userId: UUID, amount: BigDecimal) {
        jdbcTemplate
            .update(
                "UPDATE PUBLIC.\"USER\" SET BALANCE = BALANCE + ? WHERE U_ID = ?",
                amount,
                userId
            )
    }

    fun minusBalance(userId: UUID, amount: BigDecimal) {
        jdbcTemplate
            .update(
                "UPDATE PUBLIC.\"USER\" SET BALANCE = BALANCE - ? WHERE U_ID = ?",
                amount,
                userId
            )
    }

    fun getById(userId: UUID): UserDto? =
        try {
            jdbcTemplate
                .queryForObject(
                    "SELECT NAME, LAST_NAME, BALANCE, AGE FROM PUBLIC.\"USER\" WHERE U_ID = '$userId'"
                ) { rs, _ ->
                    val name = rs.getString("NAME")
                    val lastName = rs.getString("LAST_NAME")
                    val balance = BigDecimal(rs.getString("BALANCE"))
                    val age = rs.getString("AGE").toInt()
                    UserDto(userId, name, lastName, balance, age)
                }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
}