package com.example.swen3001_project_starvationprevention.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swen3001_project_starvationprevention.model.RestaurantItem
import java.util.*

@Dao
interface RestaurantItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: RestaurantItem)

    @Query("SELECT * FROM restaurantItems ORDER BY item_name ASC")
    fun getAllRestaurantItems(): List<RestaurantItem>

    @Query("SELECT * FROM restaurantItems ORDER BY item_name ASC")
    fun getAllItems(): LiveData<List<RestaurantItem>>

    @Query("SELECT * FROM restaurantItems WHERE restaurant_id = :restaurantId ORDER BY item_name ASC")
    fun getAllRestaurantItemsByRestaurantId(restaurantId: UUID): List<RestaurantItem>

    @Query("DELETE FROM restaurantItems")
    suspend fun deleteAll()
}