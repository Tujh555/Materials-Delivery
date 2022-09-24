package com.app.materialsdelivery.presentation.suppliesForTheCompany

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.materialsdelivery.databinding.FragmentSuppliesForTheCompanyBinding
import com.app.materialsdelivery.utils.appComponent
import javax.inject.Inject

class SuppliesForTheCompanyFragment : Fragment() {
    private var _binding: FragmentSuppliesForTheCompanyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: SuppliesForTheCompanyAdapter

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        )[SuppliesForTheCompanyViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuppliesForTheCompanyBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.deliveryList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun setupRecyclerView(){
        val rv = binding.recyclerView
        rv.adapter = adapter
        setupSwipeClickListener(rv)
    }

    private fun setupSwipeClickListener(rvBusinessList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteDelivery(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvBusinessList)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}