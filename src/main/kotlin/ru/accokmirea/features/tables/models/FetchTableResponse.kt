package ru.accokmirea.features.tables.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchTableResponse(
    val tables: List<TableResponse>
)

@Serializable
data class TableResponse(
    val tablesId: String,
    val name: String,
    val description: String,
    val isBooked: String
)