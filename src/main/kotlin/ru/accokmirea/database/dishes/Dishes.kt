package ru.accokmirea.database.dishes

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Dishes: Table("dishes") {
    private val dishId = Dishes.varchar("id", 50)
    private val name = Dishes.varchar("name", 50)
    private val description = Dishes.varchar("description", 150)
    private val cost = Dishes.varchar("cost", 10)

    fun insert(dishDTO: DishDTO){
        transaction {
            Dishes.insert {
                it[dishId] = dishDTO.dishId
                it[name] = dishDTO.name
                it[description] = dishDTO.description
                it[cost] = dishDTO.cost
            }
        }
    }

    fun fetchDishes(): List<DishDTO> {
        return try {
            transaction {
                Dishes.selectAll().toList()
                    .map {
                        DishDTO(
                            dishId = it[Dishes.dishId],
                            name = it[Dishes.name],
                            description = it[Dishes.description],
                            cost = it[Dishes.cost]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}