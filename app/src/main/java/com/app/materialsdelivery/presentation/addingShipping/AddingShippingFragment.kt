package com.app.materialsdelivery.presentation.addingShipping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.materialsdelivery.R

class AddingShippingFragment : Fragment() {


    private lateinit var viewModel: AddingShippingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adding_shipping, container, false)
    }


}