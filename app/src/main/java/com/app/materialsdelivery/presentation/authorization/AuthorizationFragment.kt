package com.app.materialsdelivery.presentation.authorization

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.materialsdelivery.data.mappers.toDomain
import com.app.materialsdelivery.data.realtimeDatabaseEntities.CompanyEntity
import com.app.materialsdelivery.databinding.FragmentAuthorizationBinding
import com.app.materialsdelivery.domain.entity.Company
import com.app.materialsdelivery.presentation.MainActivity
import com.app.materialsdelivery.presentation.MenuSwitcher
import com.app.materialsdelivery.utils.Constants
import com.app.materialsdelivery.utils.appComponent
import javax.inject.Inject

class AuthorizationFragment : Fragment() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private var menuSwitcher: MenuSwitcher? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(AuthorizationViewModel::class.java)
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
        _binding = FragmentAuthorizationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTextChangedListener()
        errorInput()

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            goToSuppliesFragment()
        }

        binding.input.setOnClickListener {
            viewModel.addCompany(
                binding.addNameCompanyEdit.text?.toString(),
                binding.addNameCityEdit.text.toString()
            )
        }

        binding.authorization.setOnClickListener {
            val name = binding.addNameCompanyEdit.text?.toString() ?: "empty"
            val city =  binding.addNameCityEdit.text?.toString() ?: "empty"

            Constants.currentCompany = CompanyEntity().toDomain().copy(
                id = name.hashCode(),
                name = name,
                cityName = city
            )
            goToSuppliesFragment()
        }
    }

    private fun goToSuppliesFragment() {
        AuthorizationFragmentDirections
            .actionAuthorizationFragmentToSuppliesForTheCompanyFragment()
            .let {
                findNavController().navigate(it)
            }
    }

    override fun onResume() {
        super.onResume()
        menuSwitcher?.switch(false)
    }

    private fun errorInput() {
        viewModel.errorInputCity.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Invalid name city"
            } else {
                null
            }
            binding.addNameCity.error = message
        }

        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                "Invalid name company"
            } else {
                null
            }
            binding.addNameCompany.error = message
        }
    }

    private fun addTextChangedListener() {
        binding.addNameCompanyEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        binding.addNameCityEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCity()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}