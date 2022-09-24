package com.app.materialsdelivery.data.realtimeDatabaseEntities

import com.app.materialsdelivery.data.Company

data class CompanyEntity(
    override val id: Int = -1,
    override  val name: String = "empty",
    override val cityName: String = "empty",
    override val photoUri: String? = null,
    override val individualTaxNumber: String? = null,
    override val offeredProducts: List<String>? = null,
    override val companyDescription: String? = null,
    override val foundationYear: String? = null,
    override val outgoingDeliveries: List<DeliveryEntity>? = null,
    override val incomingDeliveries: List<DeliveryEntity>? = null,
) : Company