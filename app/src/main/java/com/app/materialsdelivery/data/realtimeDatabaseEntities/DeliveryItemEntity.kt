package com.app.materialsdelivery.data.realtimeDatabaseEntities

data class DeliveryItemEntity(
    val id: Int = -1,
    val name: String = "empty",
    val units: String = "empty",
    val unitPrice: Long = -1,
)
