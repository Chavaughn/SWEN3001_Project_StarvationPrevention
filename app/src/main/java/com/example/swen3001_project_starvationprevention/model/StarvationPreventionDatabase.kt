package com.example.swen3001_project_starvationprevention.model

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.swen3001_project_starvationprevention.model.dao.CartItemsDao
import com.example.swen3001_project_starvationprevention.model.dao.FavouriteItemsDao
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantItemDao
import com.example.swen3001_project_starvationprevention.model.dao.RestaurantsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities =
    [MyCartItem::class,
    Restaurants::class,
    RestaurantItem::class,
    FavouriteItems::class],
    version = 2, exportSchema = false)
public abstract class StarvationPreventionDatabase : RoomDatabase() {

   abstract fun cartItemsDao(): CartItemsDao
   abstract fun restaurantsDao(): RestaurantsDao
   abstract fun restaurantItemDao(): RestaurantItemDao
   abstract fun favouriteItemsDao(): FavouriteItemsDao

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
                    populateDatabase(database.cartItemsDao(), database.restaurantsDao(), database.restaurantItemDao(),database.favouriteItemsDao())
                }
            }
        }

        suspend fun populateDatabase(cartItemsDao: CartItemsDao, restaurantsDao: RestaurantsDao, restaurantItemDao: RestaurantItemDao, favouriteItemsDao: FavouriteItemsDao) {
            // Delete all content here.
            cartItemsDao.deleteAll()
            restaurantsDao.deleteAll()
            restaurantItemDao.deleteAll()
            favouriteItemsDao.deleteAll()

            // Add sample Items.
            var restaurant = Restaurants("Mama's Cookshop","6:00 am","6:00 pm",1,1)
            restaurantsDao.insert(restaurant)
            restaurant = Restaurants("Papa's Cookshop","6:10 am","6:010 pm",2,3)
            restaurantsDao.insert(restaurant)
            restaurant = Restaurants("Son's Cookshop","8:10 am","5:00 pm",3,4)
            restaurantsDao.insert(restaurant)
            var item = RestaurantItem("Fried Chicken", "Mains", 1500.00, 1, UUID.randomUUID(), 1)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Fried Rice", "Mains", 1000.00, 2, UUID.randomUUID(),1)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Fried Noodles", "Mains", 1000.00, 3, UUID.randomUUID(), 2)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Fried Fish", "Mains", 2000.00, 4, UUID.randomUUID(), 3)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Bottle Water", "Drinks", 500.00, 0, UUID.randomUUID(),1)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Coca Cola", "Drinks", 500.00, 0, UUID.randomUUID(), 2)
            restaurantItemDao.insert(item)
            item = RestaurantItem("Fanta", "Drinks", 500.00, 0, UUID.randomUUID(), 3)
            restaurantItemDao.insert(item)
            var favouriteItem = FavouriteItems("Fried Chicken", "Mains", 1500.00, 1, UUID.randomUUID(), 1)
            favouriteItemsDao.insert(favouriteItem)
            favouriteItem = FavouriteItems("Fried Rice", "Mains", 1000.00, 2, UUID.randomUUID(),1)
            favouriteItemsDao.insert(favouriteItem)

        }
    }
}
