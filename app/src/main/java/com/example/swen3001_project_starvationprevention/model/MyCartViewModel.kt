package com.example.swen3001_project_starvationprevention.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.repo.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyCartViewModel(application: Application) : AndroidViewModel(application){

    private val repository: CartRepository

    val allCartItems: LiveData<List<MyCartItem>>

    init {
        val cartItemsDao = StarvationPreventionDatabase.getDatabase(application, viewModelScope).cartItemsDao()
        repository = CartRepository(cartItemsDao)
        allCartItems = repository.allStudents
    }

    fun insert(myCartItem: MyCartItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(myCartItem)
    }
}