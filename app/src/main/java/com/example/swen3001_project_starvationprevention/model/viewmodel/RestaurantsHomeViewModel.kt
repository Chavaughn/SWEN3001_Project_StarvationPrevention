package com.example.swen3001_project_starvationprevention.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.swen3001_project_starvationprevention.model.Restaurants
import com.example.swen3001_project_starvationprevention.model.RestaurantsHomeView
import com.example.swen3001_project_starvationprevention.model.StarvationPreventionDatabase
import com.example.swen3001_project_starvationprevention.model.repo.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsHomeViewModel(application: Application) : AndroidViewModel(application){

    private val repository: RestaurantRepository

    val allRestaurants: LiveData<List<Restaurants>>
    val allRestaurantData: LiveData<List<RestaurantsHomeView>>

    init {
        val restaurantsDao = StarvationPreventionDatabase.getDatabase(application, viewModelScope).restaurantsDao()
        repository = RestaurantRepository(restaurantsDao)
        allRestaurants = repository.allRestaurants
        allRestaurantData = repository.allRestaurantData
    }

    fun insert(restaurants: Restaurants) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(restaurants)
    }
}