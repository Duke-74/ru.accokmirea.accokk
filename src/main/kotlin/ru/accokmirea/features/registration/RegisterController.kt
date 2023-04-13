package ru.accokmirea.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.accokmirea.database.tokens.TokenDTO
import ru.accokmirea.database.tokens.Tokens
import ru.accokmirea.database.users.UserDTO
import ru.accokmirea.database.users.Users
import ru.accokmirea.utils.isValidLogin
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser(){

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if(!registerReceiveRemote.login.isValidLogin()){
            call.respond(HttpStatusCode.BadRequest, "Login is not valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if(userDTO != null){
            call.respond(HttpStatusCode.Conflict, "User already exist")
        } else {
            val token = UUID.randomUUID().toString()

            try{
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password
                    )
                )
            } catch (e: ExposedSQLException){
                call.respond(HttpStatusCode.Conflict, "User already exist")
            }



            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}