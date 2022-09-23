package com.app.materialsdelivery.domain.entity

data class Company(
    val id: Int,
    val name: String,
    val cityName: String,
    val photoUri: String?,
    val individualTaxNumber: String?,
    val offeredProducts: List<String>?,
    val companyDescription: String?,
    val foundationYear: String?,
    val outgoingDeliveries: List<Delivery>?,
    val incomingDeliveries: List<Delivery>?,
)
