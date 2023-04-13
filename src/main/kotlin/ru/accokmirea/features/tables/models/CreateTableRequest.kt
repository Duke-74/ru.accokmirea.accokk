package ru.accokmirea.features.tables.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateTableRequest(
    val name: String,
    val description: String,
    val isBooked: String
)

@Serializable
data class CreateTableResponse(
    val tableId: String,
    val name: String,
    val description: String,
    val isBooked: String
)