package ru.accokmirea.features.dishes.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateDishRequest(
    val name: String,
    val description: String,
    val cost: String
)

@Serializable
data class CreateDishResponse(
    val dishId: String,
    val name: String,
    val description: String,
    val cost: String
)