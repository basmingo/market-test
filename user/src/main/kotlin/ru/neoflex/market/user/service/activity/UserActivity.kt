package ru.neoflex.market.user.service.activity

import io.temporal.activity.ActivityInterface
import io.temporal.activity.ActivityMethod
import java.math.BigDecimal
import java.util.*

@ActivityInterface
interface UserActivity {

    @ActivityMethod
    fun minusUserBalance(userId: UUID, amount: BigDecimal): Boolean

    @ActivityMethod
    fun plusUserBalance(userId: UUID, amount: BigDecimal)
}
