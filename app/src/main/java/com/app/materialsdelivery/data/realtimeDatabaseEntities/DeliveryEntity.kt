package com.app.materialsdelivery.data.realtimeDatabaseEntities

data class DeliveryEntity(
    val id: Int = -1,
    val deliverySubject: DeliveryItemEntity = DeliveryItemEntity(),
    val totalAmount: Long = -1,
    val deliveryPrice: Long = -1,
    val subjectsCount: Int = 0,
    val destinationCompany: CompanyEntity? = null,
    val dispatchCompany: CompanyEntity? = null,
)
