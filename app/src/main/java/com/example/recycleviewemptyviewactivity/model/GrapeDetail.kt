package com.example.recycleviewemptyviewactivity.model

class GrapeDetail (
    var id: Int,
    val description: String?,
    val image: String?,
    var foreign_key: Int?
) {
    constructor(description: String, image: String?, foreign_key: Int?) : this(0, description, image, foreign_key)
    constructor(description: String, image: String?) : this(0, description, image, 0)
    constructor(id: Int, description: String?, image: String?) : this(0, description, image, 0)
}