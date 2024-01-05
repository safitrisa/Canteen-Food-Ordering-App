package com.example.canteenfoodorderingapp

data class riwayat(
    val id: String,
    val idBuyer: String?,
    val namaPesanan: String?,
    val quantity: Int,
    val invoice: String
) {
    constructor() : this("", "", "", 0, "")
}

