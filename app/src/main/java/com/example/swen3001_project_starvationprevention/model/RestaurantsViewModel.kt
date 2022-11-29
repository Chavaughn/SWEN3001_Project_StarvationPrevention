package com.example.swen3001_project_starvationprevention.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.repo.CartRepository
import com.example.swen3001_project_starvationprevention.model.repo.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsViewModel(application: Application) : AndroidViewModel(application){

    private val repository: RestaurantRepository

    val allRestaurants: LiveData<List<Restaurants>>

    init {
        val restaurantsDao = StarvationPreventionDatabase.getDatabase(application, viewModelScope).restaurantsDao()
        repository = RestaurantRepository(restaurantsDao)
        allRestaurants = repository.allRestaurants

    }

    fun insert(restaurants: Restaurants) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(restaurants)
    }
}