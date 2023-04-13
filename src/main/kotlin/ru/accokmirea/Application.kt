package ru.accokmirea

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.accokmirea.features.dishes.configureDishRouting
import ru.accokmirea.features.login.configureLoginRouting
import ru.accokmirea.features.registration.configureRegisterRouting
import ru.accokmirea.features.tables.configureTableRouting
import ru.accokmirea.plugins.*

fun main() {

    Database.connect(
        "jdbc:postgresql://localhost:5432/accokk-database",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0"){
        configureRouting()
        configureSerialization()
        configureLoginRouting()
        configureRegisterRouting()
        configureDishRouting()
        configureTableRouting()
    }
        .start(wait = true)
}
