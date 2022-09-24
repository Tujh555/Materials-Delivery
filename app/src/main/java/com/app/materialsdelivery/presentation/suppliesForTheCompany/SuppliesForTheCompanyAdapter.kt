package com.app.materialsdelivery.presentation.suppliesForTheCompany

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.materialsdelivery.databinding.ItemFromSuppliesForTheCompanyBinding
import com.app.materialsdelivery.domain.entity.Delivery

class SuppliesForTheCompanyAdapter :
    ListAdapter<Delivery, SuppliesForTheCompanyAdapter.ViewHolder>(SuppliesDiffCallback()) {

    class ViewHolder(private val binding: ItemFromSuppliesForTheCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(delivery: Delivery) {
                binding.run {
                    tvName.text = delivery.deliverySubject.toString()
                    tvCity.text = delivery.totalAmount.toString()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemFromSuppliesForTheCompanyBinding.inflate(layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}