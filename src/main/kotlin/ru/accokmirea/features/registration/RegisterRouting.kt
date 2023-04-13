package ru.accokmirea.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.accokmirea.features.cache.InMemoryCache
import ru.accokmirea.features.cache.TokenCache
import ru.accokmirea.features.login.LoginReceiveRemote
import ru.accokmirea.features.login.LoginResponseRemote
import ru.accokmirea.plugins.Test
import ru.accokmirea.utils.isValidLogin
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()

        }
    }
}