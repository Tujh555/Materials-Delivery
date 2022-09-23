package com.app.materialsdelivery.presentation.suppliesForTheCompany

import androidx.recyclerview.widget.DiffUtil
import com.app.materialsdelivery.domain.entity.Delivery

class DiffCallback(
    private val oldList: List<Delivery>,
    private val newList: List<Delivery>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
