package org.example.ru.neoflex.market.orchestrator.output.port.dto

import java.util.*

data class OrderDetailsResponse(val orderId: UUID, val productIds: List<UUID>)
