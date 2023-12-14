package ru.neoflex.market.warehouse.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import ru.neoflex.market.warehouse.service.dto.BookedEventDto

@Configuration
@ConfigurationProperties("spring.kafka")
class KafkaProducerConfig {

    lateinit var bootstrapServers: String

    @Bean
    fun productBookedEventTemplate(): KafkaTemplate<String, BookedEventDto> =
        KafkaTemplate(initProducerFactory(BookedEventDto::class.java))

    fun <T> initProducerFactory(type: Class<T>): ProducerFactory<String, T> {
        return DefaultKafkaProducerFactory(
            mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ), StringSerializer(), JsonSerializer<T>().apply { isAddTypeInfo = false }
        )
    }
}