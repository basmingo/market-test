package ru.neoflex.market.order

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.client.api.worker.JobHandler
import io.camunda.zeebe.spring.client.annotation.JobWorker
import io.camunda.zeebe.spring.client.annotation.Variable
import org.springframework.stereotype.Service

@Service
class OrderService {

    @JobWorker(type = "ors")
    fun fWorker(@Variable input: String): Map<String, Any> {
        println(input)
        return mapOf("out" to input + "TEST")
    }

    @JobWorker(type = "rrs")
    fun rWorker() {
        println("r end")
    }

    @JobWorker(type = "lrs")
    fun lWorker() {
        println("l end")
    }
}