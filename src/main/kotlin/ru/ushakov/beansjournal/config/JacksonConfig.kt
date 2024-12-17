package ru.ushakov.beansjournal.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.bson.types.ObjectId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        val module = SimpleModule()
        module.addSerializer(ObjectId::class.java, ObjectIdSerializer())
        mapper.registerModule(module)
        mapper.registerKotlinModule()
        mapper.registerModule(JavaTimeModule())
        return mapper
    }
}