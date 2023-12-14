package ru.neoflex.user.service

import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import org.springframework.stereotype.Service
import ru.neoflex.user.repository.UserDao
import java.math.BigDecimal
import java.util.*

@Service
class UserWorker(private val userDao: UserDao) {

    @JobWorker(type = "minusUserBalance")
    fun minusUserBalanceWorker(@Variable userId: UUID, @Variable amount: String): Map<String, Boolean> {
        println("START")
        userDao.minusBalance(userId, BigDecimal(amount))
        val currentBalance: BigDecimal? = userDao.getById(userId)?.balance
        return mapOf("isBalanceValid" to ((currentBalance?.compareTo(BigDecimal.ZERO) ?: -1) >= 0))
    }

    @JobWorker(type = "plusUserBalance")
    fun plusUserBalanceWorker(@Variable userId: UUID, @Variable amount: String) {
        userDao.plusBalance(userId, BigDecimal(amount))
    }
}