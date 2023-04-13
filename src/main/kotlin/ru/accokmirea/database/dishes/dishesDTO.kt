package ru.accokmirea.database.dishes

import ru.accokmirea.features.dishes.models.CreateDishRequest
import ru.accokmirea.features.dishes.models.CreateDishResponse
import java.util.*

@kotlinx.serialization.Serializable
class DishDTO(
    var dishId: String,
    var name: String,
    var description: String,
    var cost: String
)

fun CreateDishRequest.mapToDishDTO(): DishDTO =
    DishDTO(
        dishId = UUID.randomUUID().toString(),
        name = name,
        description = description,
        cost = cost
    )

fun DishDTO.mapToCreateDishResponse(): CreateDishResponse =
    CreateDishResponse(
        dishId = dishId,
        name = name,
        description = description,
        cost = cost
    )