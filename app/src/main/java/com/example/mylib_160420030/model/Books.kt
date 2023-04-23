package com.example.mylib_160420030.model

data class Books(
    val id: String?,
    val title: String?,
    val author: String?,
    val publisedDate: String?,
    val description: String?,
    val coverImage: String?,
    val genres: ArrayList<String>?,
    val rating: String?
)