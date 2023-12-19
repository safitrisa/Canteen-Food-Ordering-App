package com.example.canteenfoodorderingapp

data class ItemMakanMinum(
    var id: String = "", // Add 'id' field
    var namaToko: String = "",
    var nama: String = "",
    var harga: String = "",
    var stok: String = ""
) {
    constructor() : this("", "", "", "", "")
}
