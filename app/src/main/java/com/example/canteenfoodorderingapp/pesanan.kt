package com.example.canteenfoodorderingapp

data class pesanan(
    val id: String,
    val itemId: String?,
    val quantity: Int,
    val status: String
){
    constructor() : this("", "", 0, "")
}

