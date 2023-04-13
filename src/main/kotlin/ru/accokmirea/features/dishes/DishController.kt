package ru.accokmirea.features.dishes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.accokmirea.database.dishes.Dishes
import ru.accokmirea.database.dishes.mapToCreateDishResponse
import ru.accokmirea.database.dishes.mapToDishDTO
import ru.accokmirea.features.dishes.models.CreateDishRequest
import ru.accokmirea.features.dishes.models.FetchDishRequest
import ru.accokmirea.utils.TokenCheck

class DishController(private val call: ApplicationCall) {

    suspend fun fetchAllDishes(){
        val request = call.receive<FetchDishRequest>()
        val token = call.request.headers["Bearer-Authorization"]

        if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())){

            if(request.searchQuery.isBlank()){
                call.respond(Dishes.fetchDishes())
            } else {
                call.respond(Dishes.fetchDishes().filter { it.name.contains(request.searchQuery, ignoreCase = true) })
            }
        }
        else{
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

    suspend fun addDish(){
        val token = call.request.headers["Bearer-Authorization"]
        if (TokenCheck.isTokenAdmin(token.orEmpty())) {
            val request = call.receive<CreateDishRequest>()
            val dish = request.mapToDishDTO()
            Dishes.insert(dish)
            call.respond(dish.mapToCreateDishResponse())
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Token expired")
        }
    }

}