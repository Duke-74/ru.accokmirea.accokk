package ru.accokmirea.features.dishes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDishRouting() {
    routing {
        //Путь обращения к функции поиска блюда по id
        //или запрос всех существующих блюд
        post("/dishes/search") {
            val dishController = DishController(call)
            dishController.fetchAllDishes()
        }

        //Путь обработки создания нового блюда
        post("/dishes/create"){
            val dishController = DishController(call)
            dishController.addDish()
        }
    }
}