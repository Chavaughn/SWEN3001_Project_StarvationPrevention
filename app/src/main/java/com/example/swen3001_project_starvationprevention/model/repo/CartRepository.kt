package com.example.swen3001_project_starvationprevention.model.repo

import androidx.lifecycle.LiveData
import com.example.swen3001_project_starvationprevention.model.MyCartItem
import com.example.swen3001_project_starvationprevention.model.dao.CartItemsDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CartRepository(private val cartItemsDao: CartItemsDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allStudents: LiveData<List<MyCartItem>> = cartItemsDao.getAllLiveCartItems()

    suspend fun insert(myCartItem: MyCartItem) {
        cartItemsDao.insert(myCartItem)
    }
}