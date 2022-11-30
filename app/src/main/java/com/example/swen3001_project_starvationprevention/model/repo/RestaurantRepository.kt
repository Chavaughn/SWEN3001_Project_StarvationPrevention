package com.example.swen3001_project_starvationprevention.model.repo

import androidx.lifecycle.LiveData
import com.example.swen3001_project_starvationprevention.model.Restaurants
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantsDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class RestaurantRepository(private val restaurantsDao: RestaurantsDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRestaurants: LiveData<List<Restaurants>> = restaurantsDao.getAllLiveRestaurants()


    suspend fun insert(restaurants: Restaurants) {
        restaurantsDao.insert(restaurants)
    }
}