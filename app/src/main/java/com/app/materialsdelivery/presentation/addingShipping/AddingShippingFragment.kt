package com.app.materialsdelivery.presentation.addingShipping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.materialsdelivery.databinding.FragmentAddingShippingBinding
import com.app.materialsdelivery.presentation.MainActivity
import com.app.materialsdelivery.presentation.MenuSwitcher

class AddingShippingFragment : Fragment() {


    private lateinit var viewModel: AddingShippingViewModel
    private var _binding: FragmentAddingShippingBinding? = null
    private val binding get() = _binding!!
    private var menuSwitcher: MenuSwitcher? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        viewModel = ViewModelProvider(this)[AddingShippingViewModel::class.java]

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