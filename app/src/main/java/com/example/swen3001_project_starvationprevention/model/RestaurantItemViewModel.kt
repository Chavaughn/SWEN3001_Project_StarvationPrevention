package com.example.swen3001_project_starvationprevention.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.repo.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ItemRepository
    val allItems: LiveData<List<RestaurantItem>>

    init {
        val itemDao = RestaurantItemDatabase.getDatabase(application, viewModelScope).RestaurantItemsDao()
        repository = ItemRepository(itemDao)
        allItems = repository.allItems
    }

    fun addItem(item: RestaurantItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.addItem(item)
    }
}
