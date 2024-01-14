package ru.neoflex.market.orchestrator.service

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.beans.factory.annotation.Value
import ru.neoflex.market.orchestrator.workflow.CommitOrder
import ru.neoflex.market.order.OrchestratorServiceGrpcKt
import ru.neoflex.market.order.OrhcestratorService.*
import java.math.BigDecimal
import java.util.*

@GrpcService
class CommitOrderExecutorService : OrchestratorServiceGrpcKt.OrchestratorServiceCoroutineImplBase() {

    @Value("\${temporal.host}")
    lateinit var temporalHost: String

    @Value("\${temporal.port}")
    lateinit var temporalPort: String

    override suspend fun commitOrder(request: CommitOrderRequest): CommitOrderResponse {
        val options: WorkflowServiceStubsOptions = WorkflowServiceStubsOptions
            .newBuilder()
            .setTarget("$temporalHost:$temporalPort")
            .build()

        val clientOptions: WorkflowClientOptions = WorkflowClientOptions
            .newBuilder()
            .setNamespace("default")
            .build()

        val client: WorkflowClient =
            WorkflowClient.newInstance(WorkflowServiceStubs.newServiceStubs(options), clientOptions)

        val workflowOptions: WorkflowOptions = WorkflowOptions
            .newBuilder()
            .setTaskQueue("CommitOrderWorkflow")
            .build()

        val workflow = client.newWorkflowStub(CommitOrder::class.java, workflowOptions)
        workflow.execute(UUID.fromString(request.userId), BigDecimal(request.amount))
        return CommitOrderResponse.newBuilder().build()
    }
}