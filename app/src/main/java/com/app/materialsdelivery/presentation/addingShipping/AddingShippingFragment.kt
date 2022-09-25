package com.app.materialsdelivery.presentation.addingShipping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.data.realtimeDatabaseEntities.DeliveryItemEntity
import com.app.materialsdelivery.databinding.FragmentAddingShippingBinding
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.domain.entity.Delivery
import com.app.materialsdelivery.domain.entity.DeliveryItem
import com.app.materialsdelivery.presentation.MainActivity
import com.app.materialsdelivery.presentation.MenuSwitcher
import com.app.materialsdelivery.utils.Constants
import com.app.materialsdelivery.utils.appComponent
import javax.inject.Inject
import kotlin.random.Random

class AddingShippingFragment : Fragment() {
    private var _binding: FragmentAddingShippingBinding? = null
    private val binding get() = _binding!!
    private var menuSwitcher: MenuSwitcher? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(AddingShippingViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)

        if (context is MainActivity) {
            menuSwitcher = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingShippingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isAddingEnded.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "Доставка успешно добавлена",
                Toast.LENGTH_SHORT
            ).show()

            clearFields()
        }

        binding.buttonAdd.setOnClickListener {
            val price = binding.addPriceEdit.text?.toString()?.toInt() ?: 100
            val count = binding.addCountEdit.text?.toString()?.toInt() ?: 1
            val itemName = binding.addNameCompany2Edit.text.toString()
            val deliveryPrice = 250
            val destinationCompanyName = binding.addRecipientEdit.text?.toString() ?: "empty"

            val destinationCompany = CompanyEntity().toDomain().copy(
                id = destinationCompanyName.hashCode(),
                name = destinationCompanyName
            )
            val item = DeliveryItemEntity().toDomain().copy(
                id = itemName.hashCode(),
                name = itemName,
                unitPrice = price.toLong()
            )

            val delivery = Delivery(
                binding.addNameCompany2Edit.text?.hashCode() ?: Random.nextInt(0, 10000),
                item,
                price * count + deliveryPrice.toLong(),
                deliveryPrice.toLong(),
                count,
                destinationCompany,
                Constants.currentCompany
            )

            viewModel.addDelivery(delivery)
        }
    }

    private fun clearFields() {
        binding.run {
            addDescriptionEdit.setText("")
            addNameCompany2Edit.setText("")
            addRecipientEdit.setText("")
            addCountEdit.setText("")
            addPriceEdit.setText("")
        }
    }

    override fun onResume() {
        super.onResume()

        menuSwitcher?.switch(true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}