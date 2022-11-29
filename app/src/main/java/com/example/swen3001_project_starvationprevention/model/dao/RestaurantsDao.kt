package com.example.swen3001_project_starvationprevention.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swen3001_project_starvationprevention.model.Restaurants

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants ORDER BY restaurant_name ASC")
    fun getAllRestaurants(): List<Restaurants>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Restaurants)

    @Query("DELETE FROM restaurants")
    suspend fun deleteAll()

    @Query("SELECT * from restaurants ORDER BY restaurant_name ASC")
    fun getAllLiveRestaurants(): LiveData<List<Restaurants>>
}