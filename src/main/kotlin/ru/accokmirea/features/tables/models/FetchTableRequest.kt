package ru.accokmirea.features.tables.models

@kotlinx.serialization.Serializable
data class FetchTableRequest(
    val searchQuery: String
)