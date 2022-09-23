package com.app.materialsdelivery.data.realtimeDatabaseEntities

import com.app.materialsdelivery.data.Company

data class CompanyEntity(
    override val id: Int,
    override  val name: String,
    override val cityName: String,
    override val photoUri: String?,
    override val individualTaxNumber: String?,
    override val offeredProducts: List<String>?,
    override val companyDescription: String?,
    override val foundationYear: String?,
    override val outgoingDeliveries: List<DeliveryEntity>?,
    override val incomingDeliveries: List<DeliveryEntity>?,
) : Company {
    constructor(): this(
        -1,
        "empty",
        "empty",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}