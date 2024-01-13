package ru.neoflex.market.user.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.UserServiceOuterClass.*
import java.math.BigDecimal
import java.util.*

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    lateinit var userServiceImpl: UserServiceImpl

    @Autowired
    lateinit var userActivityImpl: UserActivityImpl

    @Test
    fun getUser() {
        runBlocking {
            val response = userServiceImpl
                .userCreate(
                    UserCreateRequest
                        .newBuilder()
                        .apply { name = "Pavel" }
                        .apply { lastName = "Basmanov" }
                        .apply { balance = "100.00" }
                        .apply { age = 27 }
                        .build()
                )


            userServiceImpl.getUser(
                UserRequest
                    .newBuilder()
                    .apply {
                        userId = response.userId
                    }
                    .build()
            )
        }
    }

    @Test
    fun getNonExistingUser() {
        runBlocking {
            val a = userServiceImpl.getUser(
                UserRequest
                    .newBuilder()
                    .apply {
                        userId = "${UUID.randomUUID()}"
                    }
                    .build()
            )

            assert(a.name.isEmpty())
            assert(a.lastName.isEmpty())
            assert(a.balance.isEmpty())
            assert(a.age.isEmpty())
        }
    }

    @Test
    fun userCreate() {
        runBlocking {
            userServiceImpl
                .userCreate(
                    UserCreateRequest.newBuilder()
                        .apply { name = "Pavel" }
                        .apply { lastName = "Basmanov" }
                        .apply { balance = "103.32" }
                        .apply { age = 27 }
                        .build()
                )
        }
    }

    @Test
    fun userDelete() {
        runBlocking {
            val response = userServiceImpl
                .userCreate(
                    UserCreateRequest
                        .newBuilder()
                        .apply { name = "Pavel" }
                        .apply { lastName = "Basmanov" }
                        .apply { balance = "103.32" }
                        .apply { age = 27 }
                        .build()
                )

            val responseUserId: UUID = UUID.fromString(response.userId)
            userServiceImpl.userDelete(
                UserDeleteRequest
                    .newBuilder()
                    .apply { userId = "$responseUserId" }
                    .build()
            )
        }
    }

    @Test
    fun plusMoney() {
        runBlocking {
            val response = userServiceImpl
                .userCreate(
                    UserCreateRequest
                        .newBuilder()
                        .apply { name = "Pavel" }
                        .apply { lastName = "Basmanov" }
                        .apply { balance = "100.00" }
                        .apply { age = 27 }
                        .build()
                )

            val responseUserId: UUID = UUID.fromString(response.userId)
            userServiceImpl.balanceUp(
                BalanceUpRequest
                    .newBuilder()
                    .apply { userId = "$responseUserId" }
                    .apply { amount = "132.134" }
                    .build()
            )

            val returnedUser = userServiceImpl
                .getUser(
                    UserRequest
                        .newBuilder()
                        .apply {
                            userId = "$responseUserId"
                        }
                        .build()
                )

            assert(returnedUser.balance == "232.134")
        }
    }

    @Test
    fun minusMoney() {
        runBlocking {
            val response = userServiceImpl
                .userCreate(
                    UserCreateRequest
                        .newBuilder()
                        .apply { name = "Pavel" }
                        .apply { lastName = "Basmanov" }
                        .apply { balance = "100.00" }
                        .apply { age = 27 }
                        .build()
                )

            val responseUserId: UUID = UUID.fromString(response.userId)
            userServiceImpl.balanceMinus(
                BalanceMinusRequest
                    .newBuilder()
                    .apply { userId = "$responseUserId" }
                    .apply { amount = "32.132" }
                    .build()
            )

            val returnedUser = userServiceImpl
                .getUser(
                    UserRequest
                        .newBuilder()
                        .apply {
                            userId = "$responseUserId"
                        }
                        .build()
                )

            assert(returnedUser.balance == "67.868")
        }
    }

    @Test
    fun minusMoneyCamundaWorker() {
        runBlocking {
            val response = userServiceImpl.userCreate(
                UserCreateRequest
                    .newBuilder()
                    .apply { name = "Pavel" }
                    .apply { lastName = "Basmanov" }
                    .apply { balance = "100.00" }
                    .apply { age = 27 }
                    .build()
            )

            val responseUserId: UUID = UUID.fromString(response.userId)
            val result: List<Boolean?> = listOf(99.1, 0.1, 0.72, 0.07, 0.01, 0.1)
                .map { userActivityImpl.minusUserBalance(responseUserId, BigDecimal(it)) }

            assert(result == listOf(true, true, true, true, true, false))
        }
    }
}