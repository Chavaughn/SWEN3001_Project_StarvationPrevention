package com.example.swen3001_project_starvationprevention.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    private var title = arrayOf("Fried Chicken", "Barbeque Chicken", "Chicken Rice", "Chicken Noodle", "Chicken Chop", "Chicken Burger", "Chicken Wings", "Chicken Salad", "Chicken Soup", "Chicken Curry")
    private var price = arrayOf("5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "11.00", "12.00", "13.00", "14.00")
    private var foodImage = arrayOf(R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background, R.drawable.goldenfriedchicken_background)

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImageView = itemView.findViewById<ImageView>(R.id.item_image)
        var itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)

        init{
            itemView.setOnClickListener{
                val position: Int = bindingAdapterPosition
                //val itemName = title[position]
                //val itemPrice = price[position]
                Toast.makeText(itemView.context, "Item Clicked ${title[position]}", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_card_layout, parent, false)
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val current = students[position]
        holder.itemName.text = title[position]
        holder.itemPrice.text = price[position]
        holder.itemImageView.setImageResource(foodImage[position])

    }

    override fun getItemCount(): Int {

        return title.size
    }


}