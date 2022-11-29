package com.example.swen3001_project_starvationprevention.model.repo

import androidx.lifecycle.LiveData
import com.example.swen3001_project_starvationprevention.model.RestaurantItem
import com.example.swen3001_project_starvationprevention.model.Restaurants
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantItemDao
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantsDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class RestaurantItemRepository(private val restaurantItemDao: RestaurantItemDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRestaurantItems: LiveData<List<RestaurantItem>> = restaurantItemDao.getAllLiveItems()


    suspend fun insert(restaurantItem: RestaurantItem) {
        restaurantItemDao.insert(restaurantItem)
    }
}