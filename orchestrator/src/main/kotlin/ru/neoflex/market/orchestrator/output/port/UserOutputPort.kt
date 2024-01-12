package org.example.ru.neoflex.market.orchestrator.output.port

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.math.BigDecimal
import java.util.*

@ActivityInterface
interface UserOutputPort {

    @ActivityMethod
    fun minusUserBalance(userId: UUID, amount: BigDecimal): Boolean

    @ActivityMethod
    fun plusUserBalance(userId: UUID, amount: BigDecimal)
}
