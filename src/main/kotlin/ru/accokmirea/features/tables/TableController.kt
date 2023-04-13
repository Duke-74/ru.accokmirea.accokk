package ru.accokmirea.features.tables

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.accokmirea.database.tables.Tables
import ru.accokmirea.database.tables.mapToCreateTableResponse
import ru.accokmirea.database.tables.mapToTableDTO
import ru.accokmirea.utils.TokenCheck
import ru.accokmirea.features.tables.models.FetchTableRequest
import ru.accokmirea.features.tables.models.CreateTableRequest

class TableController(private val call: ApplicationCall) {

    suspend fun fetchAllTables(){
        val request = call.receive<FetchTableRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())){

            if(request.searchQuery.isBlank()){
                call.respond(Tables.fetchTables())
            } else {
                call.respond(Tables.fetchTables().filter { it.name.contains(request.searchQuery, ignoreCase = true) })
            }
        }
        else{
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun addTable(){
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateTableRequest>()
            val table = request.mapToTableDTO()
            Tables.insert(table)
            call.respond(table.mapToCreateTableResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

}