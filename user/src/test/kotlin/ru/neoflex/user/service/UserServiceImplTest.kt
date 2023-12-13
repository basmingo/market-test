package ru.neoflex.user.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.neoflex.market.order.UserServiceOuterClass.BalanceMinusRequest
import ru.neoflex.market.order.UserServiceOuterClass.BalanceUpRequest
import ru.neoflex.market.order.UserServiceOuterClass.UserCreateRequest
import ru.neoflex.market.order.UserServiceOuterClass.UserDeleteRequest
import ru.neoflex.user.service.dto.UserDto
import java.math.BigDecimal
import java.util.*

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    lateinit var userServiceImpl: UserServiceImpl

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
                UUID.fromString(response.userId)
            )
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

            val returnedUser: UserDto? = userServiceImpl.getUser(responseUserId)
            println(returnedUser?.balance)
            assert(returnedUser?.balance == BigDecimal("232.134"))
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

            val returnedUser: UserDto? = userServiceImpl.getUser(responseUserId)
            println(returnedUser?.balance)
            assert(returnedUser?.balance == BigDecimal("67.868"))
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
            val result: List<Boolean> = listOf(99.1, 0.1, 0.72, 0.07, 0.01, 0.1)
                .map {
                userServiceImpl.minusUserBalanceWorker(responseUserId, BigDecimal("$it"))
            }

            assert(result == listOf(true, true, true, true, true, false))
        }
    }
}