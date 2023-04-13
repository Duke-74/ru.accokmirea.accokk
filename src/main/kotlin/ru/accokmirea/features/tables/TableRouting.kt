package ru.accokmirea.features.tables

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.accokmirea.features.tables.TableController

fun Application.configureTableRouting() {
    routing {
        //Путь обращения к функции поиска стола по id
        //или запрос всех существующих столов
        post("/tables/search") {
            val tableController = TableController(call)
            tableController.fetchAllTables()
        }

        //Путь обработки создания нового стола
        post("/tables/create"){
            val tableController = TableController(call)
            tableController.addTable()
        }
    }
}