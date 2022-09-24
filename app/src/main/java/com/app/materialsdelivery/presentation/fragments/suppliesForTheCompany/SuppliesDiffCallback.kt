package com.app.materialsdelivery.presentation.fragments.suppliesForTheCompany

import androidx.recyclerview.widget.DiffUtil
import com.app.materialsdelivery.domain.entity.Delivery

class SuppliesDiffCallback : DiffUtil.ItemCallback<Delivery>() {
    override fun areItemsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Delivery, newItem: Delivery): Boolean {
        return oldItem == newItem
    }

}
