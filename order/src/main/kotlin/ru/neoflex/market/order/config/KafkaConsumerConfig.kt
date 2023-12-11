package ru.neoflex.market.order.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.neoflex.market.order.service.dto.BookedEventDto
import ru.neoflex.market.order.service.dto.UnbookedEventDto

@Configuration
@ConfigurationProperties("spring.kafka")
class KafkaConsumerConfig {

    lateinit var bootstrapServers: String

    @Bean
    fun productBookedEventContainerListener() = initKafkaListnerContainerFactory(BookedEventDto::class.java)

    @Bean
    fun productUnbookedEventContainerListener() = initKafkaListnerContainerFactory(UnbookedEventDto::class.java)


    fun <T> initKafkaListnerContainerFactory(type: Class<T>): ConcurrentKafkaListenerContainerFactory<String, T> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, T>()
        factory.consumerFactory =
            DefaultKafkaConsumerFactory(
                mapOf(
                    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                    ConsumerConfig.GROUP_ID_CONFIG to 1,
                    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java
                ), StringDeserializer(), JsonDeserializer(type)
            )
        return factory
    }
}