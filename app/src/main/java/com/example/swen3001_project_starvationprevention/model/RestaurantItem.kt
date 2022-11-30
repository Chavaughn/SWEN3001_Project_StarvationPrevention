package com.example.swen3001_project_starvationprevention.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "restaurantItems")
data class RestaurantItem(var item_name:String,
                          var item_category:String,
                          var item_price:Double,
                          @PrimaryKey(autoGenerate = true) var item_id: Long,
                          var item_image_id: UUID,
                          var restaurant_id: Long,
): Parcelable
