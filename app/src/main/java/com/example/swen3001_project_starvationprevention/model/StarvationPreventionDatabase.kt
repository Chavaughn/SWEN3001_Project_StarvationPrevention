package com.example.swen3001_project_starvationprevention.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.swen3001_project_starvationprevention.model.dao.CartItemsDao
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities =
    [MyCartItem::class,
    Restaurants::class],
    version = 2, exportSchema = false)
public abstract class StarvationPreventionDatabase : RoomDatabase() {

   abstract fun cartItemsDao(): CartItemsDao
   abstract fun restaurantsDao(): RestaurantsDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StarvationPreventionDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StarvationPreventionDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StarvationPreventionDatabase::class.java,
                        "starvationprevention_database"
                    ).addCallback(StarvationPreventionDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
   }

    private class StarvationPreventionDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cartItemsDao(), database.restaurantsDao())
                }
            }
        }

        suspend fun populateDatabase(cartItemsDao: CartItemsDao, restaurantsDao: RestaurantsDao) {
            // Delete all content here.
            cartItemsDao.deleteAll()
            restaurantsDao.deleteAll()

            // Add sample Items.
            var cartItem = MyCartItem("Fried Chicken", "Mains", 1500.00, 0, UUID.randomUUID(), UUID.randomUUID())
            cartItemsDao.insert(cartItem)
            cartItem = MyCartItem("Baked Chicken", "Mains", 1700.00, 0, UUID.randomUUID(), UUID.randomUUID())
            cartItemsDao.insert(cartItem)
            var restaurant = Restaurants("Mama's Cookshop","6:00 am","6:00 pm",0,UUID.randomUUID())
            restaurantsDao.insert(restaurant)
            restaurant = Restaurants("Papa's Cookshop","6:10 am","6:010 pm",0,UUID.randomUUID())
            restaurantsDao.insert(restaurant)
        }
    }
}
