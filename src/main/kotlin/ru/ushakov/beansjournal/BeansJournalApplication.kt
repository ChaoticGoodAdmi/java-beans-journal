package ru.ushakov.beansjournal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BeansJournalApplication

fun main(args: Array<String>) {
    runApplication<BeansJournalApplication>(*args)
}
