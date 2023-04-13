package ru.accokmirea.features.dishes.models

@kotlinx.serialization.Serializable
data class FetchDishRequest(
    val searchQuery: String
)