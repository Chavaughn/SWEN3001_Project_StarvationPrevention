package com.example.swen3001_project_starvationprevention.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.FavouriteItems
import com.example.swen3001_project_starvationprevention.model.StarvationPreventionDatabase
import com.example.swen3001_project_starvationprevention.model.repo.FavouriteItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel (application: Application): AndroidViewModel(application) {
    private val repository: FavouriteItemsRepository
    val allItems: LiveData<List<FavouriteItems>>

    init {
        val itemDao = StarvationPreventionDatabase.getDatabase(application, viewModelScope).favouriteItemsDao()
        repository = FavouriteItemsRepository(itemDao)
        allItems = repository.allFavouriteItems
    }

    fun addItem(item: FavouriteItems) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    //fun deleteItem(item: FavouriteItems) = viewModelScope.launch(Dispatchers.IO) {
        //repository.delete(item)
    //}
}
