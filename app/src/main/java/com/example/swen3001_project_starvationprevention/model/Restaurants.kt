package com.example.swen3001_project_starvationprevention.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "restaurants")
data class Restaurants(var restaurant_name:String,
                       var restaurant_opening_hour:String,
                       var restaurant_closing_hour:String,
                       @PrimaryKey(autoGenerate = true) var restaurant_id: Long,
                       var signature_id: Long,
)