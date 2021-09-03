package com.slabcode.agutierrezt.example2.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.ui.listeners.OnProductListener
import com.slabcode.agutierrezt.example2.ui.adapters.ProductAdapter
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.databinding.FragmentProductBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null

    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productManger: GridLayoutManager

    private val productViewModel: ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product, container, false)
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.productRefresh.setOnRefreshListener {
            productViewModel.loadProducts()
        }

        binding.productRefresh.setSize(SwipeRefreshLayout.LARGE)
        productViewModel.loadProducts()
        productAdapter = ProductAdapter(
            listOf()
        )
        productAdapter.listener = object: OnProductListener {
            override fun onClick(product: Product) {
                Log.d("CLICK",product.name!!)
                productViewModel.selectProduct(product)
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
            }

        }
        productManger = GridLayoutManager(requireContext(), 2)
        binding.productRecycler.apply {
            adapter = productAdapter
            layoutManager = productManger
        }

        productViewModel.product.observe(viewLifecycleOwner, Observer { products ->
            binding.productRefresh.isRefreshing = false
            productAdapter.newDataSet(products)
        })
    }

}