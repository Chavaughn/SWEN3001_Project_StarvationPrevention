package com.example.swen3001_project_starvationprevention.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swen3001_project_starvationprevention.model.MyCartItem

@Dao
interface CartItemsDao {

    @Query("SELECT * FROM cartitems ORDER BY item_name ASC")
    fun getAllCartItems(): List<MyCartItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: MyCartItem)

    @Query("DELETE FROM cartitems")
    suspend fun deleteAll()

    @Query("SELECT * from cartitems ORDER BY item_name ASC")
    fun getAllLiveCartItems(): LiveData<List<MyCartItem>>
}