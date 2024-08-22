package com.example.recycleviewemptyviewactivity.model

class GrapeModel(
    var id: Int,
    val sort: String,
    val price: String?,
    val description: String?
) {
    // Secondary constructor without `id`
    constructor(sort: String, price: String?, description: String?) : this(0, sort, price, description)

}

