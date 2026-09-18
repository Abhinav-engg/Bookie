package com.abhinav.bookie.data

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val coverResId: Int,
    val category: String,
    val genre: List<String>,
    val rating: Double,
    val pageCount: Int,
    val publisher: String,
    val audioDuration: String,
    val summary: String,
    val reviewQuote: String,
    val reviewSource: String,
    val isFeatured: Boolean,
    val isFavorite: Boolean,
)
