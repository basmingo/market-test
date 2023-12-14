package ru.neoflex.market.order.repository

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.*

@Service
class OrderDao(private val jdbcTemplate: JdbcTemplate) {

    fun getByUserId(userId: UUID): OrderDto? =
        try {
            jdbcTemplate.queryForObject(
                "SELECT ORDER_ID, USER_ID, STATUS FROM PUBLIC.\"ORDER\" WHERE USER_ID = '$userId'"
            ) { rs, _ ->
                val orderId = UUID.fromString(rs.getString("ORDER_ID"))
                val status = rs.getString("STATUS")
                OrderDto(orderId, userId, status)
            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }

    fun create(order: OrderDto) {
        jdbcTemplate.update(
            "INSERT INTO PUBLIC.\"ORDER\" VALUES (?, ?, ?)",
            order.orderId,
            order.userId,
            order.status
        )
    }

    fun update(status: String, orderId: UUID) {
        jdbcTemplate.update(
            "UPDATE PUBLIC.\"ORDER\" SET STATUS = ? WHERE ORDER_ID = ?",
            status,
            orderId
        )
    }
}