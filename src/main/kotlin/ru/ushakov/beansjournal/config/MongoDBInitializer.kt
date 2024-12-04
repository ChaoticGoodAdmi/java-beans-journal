package ru.ushakov.beansmenu.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.CollectionOptions
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoDBInitializer(private val mongoTemplate: MongoTemplate) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val collections = mapOf(
            "notifications " to CollectionOptions.empty()
        )

        collections.forEach { (name, options) ->
            if (!mongoTemplate.collectionExists(name)) {
                mongoTemplate.createCollection(name, options)
                println("Created collection: $name")
            }
        }
    }
}