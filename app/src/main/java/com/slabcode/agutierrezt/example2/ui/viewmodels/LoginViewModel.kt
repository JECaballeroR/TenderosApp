package com.slabcode.agutierrezt.example2.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.slabcode.agutierrezt.example2.data.repositories.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: UserRepository): ViewModel() {
    private var _user: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?> get() = _user

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    fun signUp(email: String, name: String, pass: String) {
        viewModelScope.launch {
            try {
                _user.postValue(repo.signUp(email, name, pass))
            } catch (e: Error) {
                _error.postValue(e.message!!)
            }
        }
    }

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            try {
                _user.postValue(repo.login(email, pass))
            } catch (e: Error) {
                _error.postValue(e.message!!)
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            _user.postValue(repo.logOut())
        }
    }

    fun loggedIn() {
        viewModelScope.launch {
            _user.postValue(repo.loggedIn())
        }
    }

    fun uploadImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _user.postValue(repo.uploadImage(bitmap))
        }
    }

}