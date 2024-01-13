package ru.neoflex.market.orchestrator.output.port.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class OrderDetailsResponse @JsonCreator constructor(
    @JsonProperty("orderId") val orderId: UUID,
    @JsonProperty("productIds") val productIds: List<UUID>
)
