package com.example.swen3001_project_starvationprevention.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.model.FavouriteItems
import com.example.swen3001_project_starvationprevention.model.MyCartItem
import com.example.swen3001_project_starvationprevention.model.viewmodel.FavouritesViewModel
import com.example.swen3001_project_starvationprevention.model.viewmodel.MyCartViewModel
import java.util.*

class FavouriteAdapter internal constructor(context: Context?, viewModel: FavouritesViewModel): RecyclerView.Adapter<FavouriteAdapter.ViewHolder>(){

    private var favouriteList = emptyList<FavouriteItems>()
    private var favouritesViewModel = viewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_card_fragment, parent, false)
        return ViewHolder(view)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.fav_item_image)
        var itemName: TextView = itemView.findViewById(R.id.itemNameFavorites)
        val itemPrice: TextView = itemView.findViewById(R.id.itemCostFavorites)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescriptionFavorites)
        val delFav: Button = itemView.findViewById(R.id.removeFavItem)

        init {
            delFav.setOnClickListener{
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "Removed From Cart!", Toast.LENGTH_SHORT).show()
                val favouriteItems = FavouriteItems(favouriteList[position].item_name,favouriteList[position].item_category,favouriteList[position].item_price,favouriteList[position].item_id,
                    UUID.randomUUID(),favouriteList[position].restaurant_id)
                favouritesViewModel.deleteItem(favouriteItems)
            }
        }


    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = favouriteList[position]
        holder.itemName.text = currentItem.item_name
        holder.itemPrice.text = currentItem.item_price.toString()
        holder.itemDescription.text = currentItem.item_category
        //holder.itemImageView.setImageResource(foodImage[position])

    }
    internal fun setData(favouriteItems: List<FavouriteItems>) {
        this.favouriteList = favouriteItems
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int{
        return favouriteList.size }

        }

