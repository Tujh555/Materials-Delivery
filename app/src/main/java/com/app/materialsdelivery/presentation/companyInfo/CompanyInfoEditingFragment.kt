package com.app.materialsdelivery.presentation.companyInfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.R
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.databinding.FragmentCompanyInfoEditingBinding
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.presentation.MainActivity
import com.app.materialsdelivery.presentation.MenuSwitcher
import com.app.materialsdelivery.presentation.TakePhotoCallback
import com.app.materialsdelivery.presentation.contracts.TakePhotoContract
import com.app.materialsdelivery.utils.Constants
import com.app.materialsdelivery.utils.appComponent
import com.bumptech.glide.Glide
import javax.inject.Inject

class CompanyInfoEditingFragment : Fragment() {
    private var _binding: FragmentCompanyInfoEditingBinding? = null
    private val binding: FragmentCompanyInfoEditingBinding
        get() = requireNotNull(_binding) {
            "FragmentCompanyInfoEditingBinding was null"
        }
    private var takePhotoCallback: TakePhotoCallback? = null
    private var menuSwitcher: MenuSwitcher? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory)[CompanyInfoEditingViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)

        if (context is MainActivity) {
            takePhotoCallback = context
            menuSwitcher = context
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompanyInfoEditingBinding
            .inflate(inflater, container, false)

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrentData()

        binding.run {
            imgCompanyPhoto.setOnClickListener {

            }

            btnSubmit.setOnClickListener {
                viewModel.updateCompany(getNewCompanyInfo())
            }
        }

        viewModel.isDataUpdated.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                "Данные обновлены успешно",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.company.observe(viewLifecycleOwner) { company ->
            Constants.currentCompany = company

            binding.run {
                Glide.with(requireContext())
                    .load(company.photoUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_delivery_dining_24)
                    .into(binding.imgCompanyPhoto)

                etCompanyName.setText(company.name)
                etCompanyCity.setText(company.cityName)
                data.setText(company.foundationYear)
                companyDescription.setText(company.companyDescription)
                products.setText(company.offeredProducts?.firstOrNull())
                inn.setText(company.individualTaxNumber)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        menuSwitcher?.switch(true)
    }

    private fun getNewCompanyInfo(): Company {
        return Constants.currentCompany?.copy(
            name = binding.etCompanyName.text.toString(),
            cityName = binding.etCompanyCity.text.toString(),
            foundationYear = binding.data.text.toString(),
            companyDescription = binding.companyDescription.text.toString(),
            offeredProducts = listOf(binding.products.text.toString()),
            individualTaxNumber = binding.inn.text.toString()
        ) ?: CompanyEntity()
            .toDomain()
            .copy(
                name = binding.etCompanyName.text.toString(),
                cityName = binding.etCompanyCity.text.toString(),
                foundationYear = binding.data.text.toString(),
                companyDescription = binding.companyDescription.text.toString(),
                offeredProducts = listOf(binding.products.text.toString()),
                individualTaxNumber = binding.inn.text.toString()
            )
    }

    private fun setupCurrentData() {
        Constants.currentCompany?.let { company ->
            binding.run {
                etCompanyName.setText(company.name)
            }

            Glide.with(requireContext())
                .load(company.photoUri)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_delivery_dining_24)
                .into(binding.imgCompanyPhoto)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}