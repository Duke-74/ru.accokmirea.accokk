package ru.accokmirea.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.accokmirea.database.tokens.TokenDTO
import ru.accokmirea.database.tokens.Tokens
import ru.accokmirea.database.users.Users
import ru.accokmirea.features.cache.InMemoryCache
import ru.accokmirea.features.cache.TokenCache
import ru.accokmirea.features.registration.RegisterResponseRemote
import java.util.*

class LoginController(private val call: ApplicationCall){
    suspend fun performLogin(){
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        }else{
            if (userDTO.password == receive.password){
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowId = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            }else{
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}