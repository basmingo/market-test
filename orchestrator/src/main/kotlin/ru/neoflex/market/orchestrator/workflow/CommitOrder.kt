package ru.neoflex.market.orchestrator.workflow

import io.temporal.workflow.WorkflowInterface
import io.temporal.workflow.WorkflowMethod
import java.math.BigDecimal
import java.util.*

@WorkflowInterface
interface CommitOrder {
    @WorkflowMethod
    fun execute(userId: UUID, amount: BigDecimal)
}