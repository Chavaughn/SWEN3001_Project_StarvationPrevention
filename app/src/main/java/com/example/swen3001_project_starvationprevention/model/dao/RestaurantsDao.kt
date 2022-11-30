package com.example.swen3001_project_starvationprevention.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swen3001_project_starvationprevention.model.Restaurants
import com.example.swen3001_project_starvationprevention.model.RestaurantsHomeView

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants ORDER BY restaurant_id ASC")
    fun getAllRestaurants(): List<Restaurants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Restaurants)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAll()

    @Query("SELECT * from restaurants ORDER BY restaurant_id ASC")
    fun getAllLiveRestaurants(): LiveData<List<Restaurants>>

    @Query("SELECT restaurantItems.item_name, restaurantItems.item_price, restaurantItems.item_id, restaurantItems.item_category, restaurant_name, restaurant_closing_hour, restaurant_opening_hour, restaurants.restaurant_id FROM restaurantItems INNER JOIN restaurants ON restaurants.signature_id = restaurantItems.item_id")
    fun getRestaurantData(): LiveData<List<RestaurantsHomeView>>

}