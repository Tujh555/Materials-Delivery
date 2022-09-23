package com.app.materialsdelivery.data.realtimeDatabaseEntities

data class DeliveryItemEntity(
    val id: Int,
    val name: String,
    val units: String,
    val unitPrice: Long,
) {
    constructor(): this(
        -1,
        "empty",
        "empty",
        -1
    )
}
