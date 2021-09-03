package com.slabcode.agutierrezt.example2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.repositories.CommentRepository
import kotlinx.coroutines.launch

class CommentViewModel(private val repo: CommentRepository): ViewModel() {
    private var _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val comments: LiveData<List<Comment>> get() = _comments

    fun loadComments() {
        viewModelScope.launch {
            _comments.postValue(repo.loadComments())
        }
    }
}