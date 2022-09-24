package com.app.materialsdelivery.data.mappers

import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryItemEntity
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.entity.DeliveryItem

fun Company.toEntity(): CompanyEntity = CompanyEntity(
    id,
    name,
    cityName,
    photoUri,
    individualTaxNumber,
    offeredProducts,
    companyDescription,
    foundationYear,
    outgoingDeliveries?.map { it.toEntity() },
    incomingDeliveries?.map { it.toEntity() },
)

fun Delivery.toEntity(): DeliveryEntity = DeliveryEntity(
    id,
    deliverySubject.toEntity(),
    totalAmount,
    deliveryPrice,
    subjectsCount,
    destinationCompany?.toEntity(),
    dispatchCompany?.toEntity(),
)

fun DeliveryItem.toEntity(): DeliveryItemEntity = DeliveryItemEntity(
    id,
    name,
    units,
    unitPrice
)

fun DeliveryEntity.toDomain(): Delivery = Delivery(
    id,
    deliverySubject.toDomain(),
    totalAmount,
    deliveryPrice,
    subjectsCount,
    destinationCompany?.toDomain(),
    dispatchCompany?.toDomain()
)

fun DeliveryItemEntity.toDomain(): DeliveryItem = DeliveryItem(
    id,
    name,
    units,
    unitPrice
)

fun CompanyEntity.toDomain(): Company = Company(
    id,
    name,
    cityName,
    photoUri,
    individualTaxNumber,
    offeredProducts,
    companyDescription,
    foundationYear,
    outgoingDeliveries?.map { it.toDomain() },
    incomingDeliveries?.map { it.toDomain() },
)
