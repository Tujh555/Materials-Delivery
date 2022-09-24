package com.app.materialsdelivery.presentation.companyInfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.R
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.databinding.FragmentCompanyInfoEditingBinding
import com.app.materialsdelivery.domain.entity.Company
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

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var takePhotoContract: TakePhotoContract

    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(CompanyInfoEditingViewModel::class.java)
    }

    private val updatePhoto by lazy {
        registerForActivityResult(takePhotoContract) {
            viewModel.updateCompanyPhoto(it)
            setupCurrentData()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
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
                updatePhoto.launch(null)
            }

            btnSubmit.setOnClickListener {
                viewModel.updateCompany(getNewCompanyInfo())
            }
        }
    }

    private fun getNewCompanyInfo(): Company {
        return Constants.currentCompany?.copy(
            name = binding.etCompanyName.text.toString()
        ) ?: CompanyEntity()
            .toDomain()
            .copy(name = binding.etCompanyName.text.toString())
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