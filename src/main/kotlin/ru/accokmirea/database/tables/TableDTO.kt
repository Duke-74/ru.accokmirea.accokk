package ru.accokmirea.database.tables

import ru.accokmirea.features.dishes.models.CreateDishRequest
import ru.accokmirea.features.dishes.models.CreateDishResponse
import ru.accokmirea.features.tables.models.CreateTableRequest
import ru.accokmirea.features.tables.models.CreateTableResponse
import java.util.*

@kotlinx.serialization.Serializable
class TableDTO(
    var tableId: String,
    var name: String,
    var description: String,
    var isBooked: String
)

fun CreateTableRequest.mapToTableDTO(): TableDTO =
    TableDTO(
        tableId = UUID.randomUUID().toString(),
        name = name,
        description = description,
        isBooked = isBooked
    )

fun TableDTO.mapToCreateTableResponse(): CreateTableResponse =
    CreateTableResponse(
        tableId = tableId,
        name = name,
        description = description,
        isBooked = isBooked
    )