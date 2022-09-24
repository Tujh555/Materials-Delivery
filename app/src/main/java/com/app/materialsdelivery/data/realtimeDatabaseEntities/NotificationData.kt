package com.app.materialsdelivery.data.realtimeDatabaseEntities

 class NotificationData(
     val deliveryId: Int = -1,
     val companyName: String = "empty",
     val notifiedCompanyName: String = "empty"
) {
     override fun toString() = "Посылка №$deliveryId, от компании $companyName, доставлена."
}
