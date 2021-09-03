package com.slabcode.agutierrezt.example2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.data.models.StoreInfo
import com.slabcode.agutierrezt.example2.data.repositories.CommentRepository
import com.slabcode.agutierrezt.example2.data.repositories.HomeRepository
import com.slabcode.agutierrezt.example2.data.repositories.ProductRepository
import com.slabcode.agutierrezt.example2.data.repositories.UserRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val commentRepo: CommentRepository,
    private val productRepo: ProductRepository,
    private val storeRepo: HomeRepository,
    private val userRepo: UserRepository
): ViewModel() {

    private var _user: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    fun insert(comments: List<Comment>, products: List<Product>, store: StoreInfo) {
        viewModelScope.launch {
            commentRepo.insertComments(comments)
            productRepo.insertProducts(products)
            storeRepo.insertStore(store)
        }
    }

    fun loggedIn() {
        viewModelScope.launch {
            _user.postValue(userRepo.loggedIn())
        }
    }

}