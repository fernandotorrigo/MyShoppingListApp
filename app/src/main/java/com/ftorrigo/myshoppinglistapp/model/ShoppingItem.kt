package com.ftorrigo.myshoppinglistapp.model

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditin: Boolean = false
)
