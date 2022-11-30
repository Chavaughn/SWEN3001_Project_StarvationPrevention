package com.example.swen3001_project_starvationprevention.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.model.FavouriteItems

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    private var favouriteList = emptyList<FavouriteItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_card_fragment, parent, false)
        return ViewHolder(view)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.fav_item_image)
        var itemName: TextView = itemView.findViewById(R.id.itemNameFavorites)
        val itemPrice: TextView = itemView.findViewById(R.id.itemCostFavorites)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescriptionFavorites)
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

