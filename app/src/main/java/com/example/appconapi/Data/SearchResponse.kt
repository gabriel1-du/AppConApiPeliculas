package com.example.appconapi.Data


data class SearchResponse(
    val Search: List<MovieItem>?,
    val totalResults: String?,
    val Response: String
)
