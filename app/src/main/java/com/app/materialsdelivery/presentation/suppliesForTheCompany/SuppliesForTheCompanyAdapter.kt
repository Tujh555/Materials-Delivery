package com.app.materialsdelivery.presentation.suppliesForTheCompany

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.materialsdelivery.databinding.ItemFromSuppliesForTheCompanyBinding
import com.app.materialsdelivery.domain.entity.Delivery

class SuppliesForTheCompanyAdapter :
    RecyclerView.Adapter<SuppliesForTheCompanyAdapter.ViewHolder>() {

    var suppliesList = listOf<Delivery>()
        set(value) {
            val callback = DiffCallback(suppliesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class ViewHolder(val binding: ItemFromSuppliesForTheCompanyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemFromSuppliesForTheCompanyBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {

        }

    }

    override fun getItemCount(): Int = suppliesList.size
}