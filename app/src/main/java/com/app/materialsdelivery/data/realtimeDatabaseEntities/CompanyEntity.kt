package com.app.materialsdelivery.data.realtimeDatabaseEntities

data class CompanyEntity(
    val id: Int,
    val name: String,
    val cityName: String,
    val photoUri: String?,
    val individualTaxNumber: String?,
    val offeredProducts: List<String>?,
    val companyDescription: String?,
    val foundationYear: String?,
    val outgoingDeliveries: List<DeliveryEntity>?,
    val incomingDeliveries: List<DeliveryEntity>?,
)