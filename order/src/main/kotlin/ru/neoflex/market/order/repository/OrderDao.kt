package ru.neoflex.market.order.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Service
import ru.neoflex.market.order.service.dto.OrderDto
import java.util.UUID

@Service
class OrderDao(private val jdbcTemplate: JdbcTemplate) {

    fun getByUserId(userId: UUID): OrderDto {
        println("in func - $userId")
        val sql = "SELECT ORDER_ID, USER_ID, STATUS FROM PUBLIC.\"ORDER\" WHERE USER_ID = '$userId'"

        println("string - $sql")
        val r = jdbcTemplate.queryForObject<OrderDto>(sql)
        println("r - $r")
        return r
    }

    fun create(order: OrderDto) {
        jdbcTemplate.update(
            "INSERT INTO PUBLIC.\"ORDER\" VALUES (?, ?, ?)",
            order.orderId,
            order.userId,
            order.status
        )
    }
}