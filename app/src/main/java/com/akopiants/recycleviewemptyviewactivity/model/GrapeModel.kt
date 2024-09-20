package com.akopiants.recycleviewemptyviewactivity.model

class GrapeModel(
    var id: Int,
    val sort: String,
    val fav: Int?
) {
    // Secondary constructor without `id`
    constructor(sort: String, fav: Int?) : this(0, sort, fav)
    constructor(sort: String) : this(0, sort, 0)
}

