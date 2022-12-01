package com.example.swen3001_project_starvationprevention.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swen3001_project_starvationprevention.model.FavouriteItems

@Dao
interface FavouriteItemsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FavouriteItems)

    @Query("SELECT * FROM favouriteItems ORDER BY item_name ASC")
    fun getAllFavouriteItems(): List<FavouriteItems>

    @Query("SELECT * FROM favouriteItems ORDER BY item_name ASC")
    fun getAllLiveFavouriteItems(): LiveData<List<FavouriteItems>>

    @Query("DELETE FROM favouriteItems")
    suspend fun deleteAll()

    @Query("DELETE FROM favouriteItems WHERE item_id = :item_id")
    suspend fun delete(item_id: Long)


}