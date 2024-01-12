package org.example.ru.neoflex.market.orchestrator.workflow

import java.math.BigDecimal
import java.util.*

interface CommitOrder {
    fun execute(userId: UUID, amount: BigDecimal)
}