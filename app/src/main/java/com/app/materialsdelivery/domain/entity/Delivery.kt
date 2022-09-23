package com.app.materialsdelivery.domain.entity

data class Delivery(
    val id: Int,
    val deliverySubject: DeliveryItem,
    val totalAmount: Long,
    val deliveryPrice: Long,
    val subjectsCount: Int,
    val destinationCompany: Company,
    val dispatchCompany: Company,
)
