package com.example.mylib_160420030.model

data class Books(
    val id: Int,
    val title: String?,
    val author: String?,
    val publishedDate: String?,
    val description: String?,
    val coverImage: String?,
    val genres: ArrayList<String>?,
    val rating: Double?
)