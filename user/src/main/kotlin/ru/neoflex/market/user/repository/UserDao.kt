package ru.neoflex.market.user.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import ru.neoflex.market.user.service.dto.UserDto
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
                    UserDto(
                        userId = userId,
                        name = rs.getString("NAME"),
                        lastName = rs.getString("LAST_NAME"),
                        balance = BigDecimal(rs.getString("BALANCE")),
                        age = rs.getString("AGE").toInt()
                    )
                }
        } catch (e: EmptyResultDataAccessException) {
            null
        }

    fun getAll(): List<UserDto> =
        jdbcTemplate
            .query(
                "SELECT U_ID, NAME, LAST_NAME, BALANCE, AGE FROM PUBLIC.\"USER\""
            ) { rs, _ ->
                UserDto(
                    userId = UUID.fromString(rs.getString("U_ID")),
                    name = rs.getString("NAME"),
                    lastName = rs.getString("LAST_NAME"),
                    balance = BigDecimal(rs.getString("BALANCE")),
                    age = rs.getString("AGE").toInt()
                )
            }
}