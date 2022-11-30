package com.example.swen3001_project_starvationprevention.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.model.MyCartItem
import com.example.swen3001_project_starvationprevention.model.RestaurantItem
import com.example.swen3001_project_starvationprevention.model.Restaurants
import com.example.swen3001_project_starvationprevention.model.RestaurantsHomeView

class HomeRestaurantsAdapter internal constructor(context: Context?): RecyclerView.Adapter<HomeRestaurantsAdapter.RestaurantsViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var restaurants = emptyList<RestaurantsHomeView>() // Cached copy of Restaurants
    private var cartItem = emptyList<MyCartItem>()

    inner class RestaurantsViewHolder(restaurant: View) : RecyclerView.ViewHolder(restaurant) {
        val restaurantName: TextView = itemView.findViewById(R.id.restaurant_Name)
        val restaurantOpening: TextView = itemView.findViewById(R.id.openingHour)
        val restaurantClosing: TextView = itemView.findViewById(R.id.closingHour)
        val specialtyName: TextView = itemView.findViewById(R.id.specialtyName)
        val specialtyPrice: TextView = itemView.findViewById(R.id.specialtyPrice)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartHome)

        init{

            restaurantName.setOnClickListener{
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "Restaurant ${restaurants[position].restaurant_name} clicked", Toast.LENGTH_SHORT).show()
                restaurant.findNavController().navigate(R.id.action_homeFragment_to_restaurantFragment)
            }

            addToCartButton.setOnClickListener{
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "Added to cart!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val restaurant = inflater.inflate(R.layout.home_restaurants_fragment, parent, false)


        return RestaurantsViewHolder(restaurant)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val current = restaurants[position]
        holder.restaurantName.text = current.restaurant_name
        holder.restaurantOpening.text = current.restaurant_opening_hour
        holder.restaurantClosing.text = current.restaurant_closing_hour
        holder.specialtyName.text = current.item_name
        holder.specialtyPrice.text = "$${current.item_price}"


    }

    internal fun setRestaurantsHome(restaurants: List<RestaurantsHomeView>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    override fun getItemCount() = restaurants.size
}