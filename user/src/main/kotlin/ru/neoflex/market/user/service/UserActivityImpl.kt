package ru.neoflex.market.user.service

import org.springframework.stereotype.Service
import ru.neoflex.market.user.repository.UserDao
import ru.neoflex.market.user.service.activity.UserActivity
import java.math.BigDecimal
import java.util.*

@Service
class UserActivityImpl(private val userDao: UserDao) : UserActivity {

    override fun minusUserBalance(userId: UUID, amount: BigDecimal): Boolean {
        userDao.minusBalance(userId, amount)
        val currentBalance: BigDecimal? = userDao.getById(userId)?.balance
        if ((currentBalance?.compareTo(BigDecimal.ZERO) ?: -1) < 0) {
            throw RuntimeException("Balance of user is not enough")
        }

        return (currentBalance?.compareTo(BigDecimal.ZERO) ?: -1) >= 0
    }

    override fun plusUserBalance(userId: UUID, amount: BigDecimal) {
        userDao.plusBalance(userId, amount)
    }
}