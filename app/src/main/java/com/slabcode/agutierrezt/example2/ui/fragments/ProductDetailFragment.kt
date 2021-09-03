package com.slabcode.agutierrezt.example2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.databinding.FragmentProductDetailBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null

    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_product_detail, container, false)
        _binding =  FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        productViewModel.selected.observe(viewLifecycleOwner, Observer { product ->
            binding.productDetailName.text = product.name
            binding.productDetailDescription.text = product.description
            Glide.with(binding.root).load(product.image).into(binding.productDetailImage)
        })
    }


}