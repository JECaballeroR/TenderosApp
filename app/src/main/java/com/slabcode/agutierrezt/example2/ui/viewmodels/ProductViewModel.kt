package com.slabcode.agutierrezt.example2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.data.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repo: ProductRepository): ViewModel() {
    private var _products: MutableLiveData<List<Product>> = MutableLiveData()
    val product: LiveData<List<Product>> get() = _products

    private var _selected: MutableLiveData<Product> = MutableLiveData()
    val selected: LiveData<Product> get() = _selected

    fun loadProducts() {
        viewModelScope.launch {
            _products.postValue(repo.loadProducts())
        }
    }

    fun selectProduct(product: Product) {
        _selected.postValue(product)
    }

}