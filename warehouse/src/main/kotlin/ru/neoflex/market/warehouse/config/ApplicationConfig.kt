package ru.neoflex.market.warehouse.config

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.Worker
import io.temporal.worker.WorkerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.neoflex.market.warehouse.service.ProductActivityImpl

@Configuration
class ApplicationConfig {

    @Value("\${temporal.host}")
    lateinit var temporalHost: String

    @Value("\${temporal.worker-name}")
    lateinit var temporalWorkerName: String

    @Value("\${temporal.namespace}")
    lateinit var temporalNamespace: String

    @Value("\${temporal.port}")
    lateinit var temporalPort: String

    @Autowired
    lateinit var productActivity: ProductActivityImpl

    @Bean
    @ConditionalOnProperty(prefix = "temporal", name = ["enabled"], havingValue = "true")
    fun productActivityWorker(): Worker {
        val options: WorkflowServiceStubsOptions = WorkflowServiceStubsOptions
            .newBuilder()
            .setTarget("$temporalHost:$temporalPort")
            .build()

        val clientOptions: WorkflowClientOptions = WorkflowClientOptions
            .newBuilder()
            .setNamespace(temporalNamespace)
            .build()

        val client: WorkflowClient =
            WorkflowClient.newInstance(WorkflowServiceStubs.newServiceStubs(options), clientOptions)
        val workerFactory = WorkerFactory.newInstance(client)
        val worker: Worker = workerFactory.newWorker(temporalWorkerName)
        worker.registerActivitiesImplementations(productActivity)
        workerFactory.start()
        return worker
    }
}