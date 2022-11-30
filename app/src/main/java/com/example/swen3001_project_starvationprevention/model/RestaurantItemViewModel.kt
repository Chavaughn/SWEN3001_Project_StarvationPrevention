package com.example.swen3001_project_starvationprevention.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.repo.RestaurantItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RestaurantItemRepository
    val allItems: LiveData<List<RestaurantItem>>


    init {
        val itemDao = StarvationPreventionDatabase.getDatabase(application, viewModelScope).restaurantItemDao()
        repository = RestaurantItemRepository(itemDao)
        allItems = repository.allRestaurantItems
    }

   fun getFilteredItems(category: String): LiveData<List<RestaurantItem>> {
        return repository.getFilteredItems(category)
    }

    fun addItem(item: RestaurantItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}
