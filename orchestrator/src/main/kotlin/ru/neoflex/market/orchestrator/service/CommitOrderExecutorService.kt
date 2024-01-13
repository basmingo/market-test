package ru.neoflex.market.orchestrator.service

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.client.WorkflowOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import net.devh.boot.grpc.server.service.GrpcService
import ru.neoflex.market.orchestrator.workflow.CommitOrder
import ru.neoflex.market.order.OrchestratorServiceGrpcKt
import ru.neoflex.market.order.OrhcestratorService.*
import java.math.BigDecimal
import java.util.*

@GrpcService
class CommitOrderExecutorService : OrchestratorServiceGrpcKt.OrchestratorServiceCoroutineImplBase() {
    override suspend fun commitOrder(request: CommitOrderRequest): CommitOrderResponse {
        val client: WorkflowClient = WorkflowClient.newInstance(
            WorkflowServiceStubs
                .newLocalServiceStubs(),
            WorkflowClientOptions
                .newBuilder()
                .setNamespace("default")
                .build()
        )

        val options: WorkflowOptions = WorkflowOptions
            .newBuilder()
            .setTaskQueue("CommitOrderWorkflow")
            .build()

        val workflow = client.newWorkflowStub(CommitOrder::class.java, options)
        workflow.execute(UUID.fromString(request.userId), BigDecimal(request.amount))
        return CommitOrderResponse.newBuilder().build()
    }
}