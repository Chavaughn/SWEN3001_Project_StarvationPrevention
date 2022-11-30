package com.example.swen3001_project_starvationprevention.adapter

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
import com.example.swen3001_project_starvationprevention.fragment.RestaurantFragmentDirections
import com.example.swen3001_project_starvationprevention.model.RestaurantItem

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private var itemList = emptyList<RestaurantItem>()

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImageView = itemView.findViewById<ImageView>(R.id.item_image)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)

        init{
            itemView.setOnClickListener{

                val position: Int = bindingAdapterPosition
                //val action = restaurantFragmentDirections
                val action = RestaurantFragmentDirections.actionRestaurantFragmentToItemFragment(currentItem = itemList[position])
                itemView.findNavController().navigate(action)


                //val itemName = title[position]
                //val itemPrice = price[position]
                Toast.makeText(itemView.context, "Item Clicked ${itemList[position]}", Toast.LENGTH_SHORT).show()

            //findNavController(View ,1).navigate(R.id.action_restaurantFragment_to_itemFragment)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_card_layout, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemName.text = currentItem.item_name
        holder.itemPrice.text = currentItem.item_price.toString()
        //holder.itemImageView.setImageResource(foodImage[position])

    }

    override fun getItemCount(): Int {

        return itemList.size
    }
    fun setData(restaurantItem: List<RestaurantItem>){
        this.itemList = restaurantItem
        notifyDataSetChanged()
    }


}