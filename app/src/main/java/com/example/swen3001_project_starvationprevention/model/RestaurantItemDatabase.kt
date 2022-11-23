package com.example.swen3001_project_starvationprevention.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.swen3001_project_starvationprevention.model.dao.CartItemsDao
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [RestaurantItem::class, MyCartItem::class], version = 1, exportSchema = false)

abstract class RestaurantItemDatabase: RoomDatabase() {
    abstract fun RestaurantItemsDao(): RestaurantItemDao
    abstract fun cartItemsDao(): CartItemsDao

    companion object{
        @Volatile
        private var INSTANCE: RestaurantItemDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): RestaurantItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantItemDatabase::class.java,
                    "item_database"
                ).addCallback(RestaurantItemDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class RestaurantItemDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.RestaurantItemsDao())
                }
            }
        }

        suspend fun populateDatabase(itemDao: RestaurantItemDao) {
            // Delete all content here.
            itemDao.deleteAll()

            // Add sample students.
            var item = RestaurantItem("Fried Chicken", "Mains", 1500.00, 0, UUID.randomUUID(), UUID.randomUUID())
            itemDao.insert(item)
            item = RestaurantItem("Fried Rice", "Mains", 1000.00, 0, UUID.randomUUID(), UUID.randomUUID())
            itemDao.insert(item)
            item = RestaurantItem("Fried Noodles", "Mains", 1000.00, 0, UUID.randomUUID(), UUID.randomUUID())
            itemDao.insert(item)
            item = RestaurantItem("Fried Fish", "Mains", 2000.00, 0, UUID.randomUUID(), UUID.randomUUID())
            itemDao.insert(item)
        }
    }
}