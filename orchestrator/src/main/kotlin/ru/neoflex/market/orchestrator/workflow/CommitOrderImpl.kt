package ru.neoflex.market.orchestrator.workflow

import io.temporal.activity.ActivityOptions
import io.temporal.failure.ActivityFailure
import io.temporal.workflow.Workflow
import ru.neoflex.market.orchestrator.output.port.OrderOutputPort
import ru.neoflex.market.orchestrator.output.port.ProductOutputPort
import ru.neoflex.market.orchestrator.output.port.UserOutputPort
import java.math.BigDecimal
import java.time.Duration
import java.util.*

class CommitOrderImpl : CommitOrder {

    private val orderService: OrderOutputPort = Workflow.newActivityStub(
        OrderOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("OrderServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
    )

    private val userService: UserOutputPort = Workflow.newActivityStub(
        UserOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("UserServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
        )
    private val productService: ProductOutputPort = Workflow.newActivityStub(
        ProductOutputPort::class.java,
        ActivityOptions
            .newBuilder()
            .setTaskQueue("ProductServiceActivity")
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build()
    )

    override fun execute(userId: UUID, amount: BigDecimal) {
        try {
            val orderDetails = orderService.getOrderDetails(userId)

            val isBalanceEnough = userService.minusUserBalance(userId, amount)
            if (!isBalanceEnough) {
                userService.plusUserBalance(userId, amount)
            }

            orderService.setStatusToDelivery(orderDetails.orderId)
            try {
                productService.toDeliveryProduct(orderDetails.productIds)
            } catch (e: ActivityFailure) {
                productService.onAvailableProduct(orderDetails.productIds)
                userService.plusUserBalance(userId, amount)
            }

        } catch (e: ActivityFailure) {
            println("FAILURE")
        }

    }
}