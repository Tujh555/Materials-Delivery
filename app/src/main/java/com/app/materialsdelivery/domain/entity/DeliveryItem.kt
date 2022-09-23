package com.app.materialsdelivery.domain.entity

data class DeliveryItem(
    val id: Int,
    val name: String,
    val units: String,
    val unitPrice: Long,
)