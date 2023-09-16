package com.jeff_skillrill.book_shop_application.model

data class Book(
    var name: String,
    var author: String,
    var pages: Int,
    var description: String,
    var rating: Double,
    var size: String,
    var price: String,
    var img: Int,
    var genreName: String,
    var reviews: ArrayList<Review>?,
    var isSaved: Boolean = false,
    var isWish: Boolean = false,
) : java.io.Serializable
