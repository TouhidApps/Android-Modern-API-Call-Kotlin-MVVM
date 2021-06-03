package com.touhidapps.modernApi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.touhidapps.modernApi.adapter.MobileOperatorAdapter
import com.touhidapps.modernApi.databinding.FragmentItemBinding
import com.touhidapps.modernApi.model.ItemsModel
import com.touhidapps.modernApi.networkService.ApiState
import com.touhidapps.modernApi.repository.ItemRepository
import com.touhidapps.modernApi.viewModel.ItemViewModel
import com.touhidapps.modernApi.viewModel.ItemViewModelFactory
import kotlinx.coroutines.flow.collect

class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var mobileOperatorAdapter : MobileOperatorAdapter
    private var items = arrayListOf<ItemsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        itemViewModel = ViewModelProvider(
            this,
            ItemViewModelFactory(ItemRepository())
        )[ItemViewModel::class.java]

    } // onCreate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    } // onCreateView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        collects() // Like observers of live data

        listeners()

    } // onViewCreated

    private fun initUI() {

        mobileOperatorAdapter = MobileOperatorAdapter(items)

        binding.rvFlowers.adapter = mobileOperatorAdapter

        binding.srlFlowers.post {
            itemViewModel.getFlowerList("3")
        }
        binding.srlFlowers.setOnRefreshListener {
            itemViewModel.getFlowerList("3")
        }

    } // initUI

    private fun collects() {

        lifecycleScope.launchWhenCreated {
            itemViewModel.wMessage.collect {
                when (it) {
                    is ApiState.Loading -> {
                        binding.srlFlowers.isRefreshing = true
                    }
                    is ApiState.Failure -> {
                        it.e.printStackTrace()
                        binding.srlFlowers.isRefreshing = false
                    }
                    is ApiState.Success -> {

                        binding.srlFlowers.isRefreshing = false
                        val myObj = it.data as List<ItemsModel>
                        items.clear()
                        items.addAll(myObj)
                        mobileOperatorAdapter.notifyDataSetChanged()

                    }
                    is ApiState.Empty -> {
                        println("Empty...")
                    }
                }
            }
        }

    } // collects

    private fun listeners() {
        mobileOperatorAdapter.setItemClick {
            Toast.makeText(activity, "Clicked ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

}