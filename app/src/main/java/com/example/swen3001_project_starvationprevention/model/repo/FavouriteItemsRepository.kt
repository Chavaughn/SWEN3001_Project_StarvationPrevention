package com.example.swen3001_project_starvationprevention.model.repo

import androidx.lifecycle.LiveData
import com.example.swen3001_project_starvationprevention.model.FavouriteItems
import com.example.swen3001_project_starvationprevention.model.dao.FavouriteItemsDao

class FavouriteItemsRepository(private val favouriteItemsDao: FavouriteItemsDao) {


    val allFavouriteItems: LiveData<List<FavouriteItems>> = favouriteItemsDao.getAllLiveFavouriteItems()


    suspend fun insert(item: FavouriteItems) {
        favouriteItemsDao.insert(item)
    }

    suspend fun delete(item: FavouriteItems) {
        favouriteItemsDao.delete(item.item_id)
    }

    suspend fun deleteAll() {
        favouriteItemsDao.deleteAll()
    }
}