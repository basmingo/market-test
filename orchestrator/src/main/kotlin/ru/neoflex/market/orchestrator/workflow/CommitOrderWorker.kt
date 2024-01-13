package ru.neoflex.market.orchestrator.workflow

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.worker.WorkerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CommitOrderWorker {
    val client: WorkflowClient = WorkflowClient.newInstance(
        WorkflowServiceStubs
            .newLocalServiceStubs(),

        WorkflowClientOptions
            .newBuilder()
            .setNamespace("default")
            .build()
    )

    @PostConstruct
    fun workerStart() {
        val workerFactory = WorkerFactory.newInstance(client)
        val worker = workerFactory.newWorker("CommitOrderWorkflow")
        worker.registerWorkflowImplementationTypes(CommitOrderImpl::class.java)
        workerFactory.start()
    }
}