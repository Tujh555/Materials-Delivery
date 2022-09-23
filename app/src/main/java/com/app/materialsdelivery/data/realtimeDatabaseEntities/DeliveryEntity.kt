package com.app.materialsdelivery.data.realtimeDatabaseEntities

data class DeliveryEntity(
    val id: Int,
    val deliverySubject: DeliveryItemEntity,
    val totalAmount: Long,
    val deliveryPrice: Long,
    val subjectsCount: Int,
    val destinationCompany: CompanyEntity?,
    val dispatchCompany: CompanyEntity?,
) {
    constructor(): this(
        -1,
        DeliveryItemEntity(),
        -1,
        -1,
        0,
        null,
        null
    )
}
