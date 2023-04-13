package ru.accokmirea.features.dishes.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchDishResponse(
    val dishes: List<DishResponse>
)

@Serializable
data class DishResponse(
    val dishId: String,
    val name: String,
    val description: String,
    val cost: String
)