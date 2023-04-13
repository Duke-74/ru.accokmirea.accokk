package ru.accokmirea.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.accokmirea.features.cache.InMemoryCache
import ru.accokmirea.features.cache.TokenCache
import ru.accokmirea.features.registration.RegisterReceiveRemote
import ru.accokmirea.plugins.Test
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}