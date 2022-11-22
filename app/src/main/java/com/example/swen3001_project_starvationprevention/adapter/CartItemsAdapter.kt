package com.example.swen3001_project_starvationprevention.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.model.MyCartItem

class CartItemsAdapter internal constructor(context: Context?): RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var myCartItems = emptyList<MyCartItem>() // Cached copy of Students

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemNameCart)
        val itemPrice: TextView = itemView.findViewById(R.id.itemCostCart)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescriptionCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val itemView = inflater.inflate(R.layout.cart_items_fragment, parent, false)
        return CartItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val current = myCartItems[position]
        holder.itemName.text = current.item_name
        holder.itemPrice.text = current.item_price.toString()
        holder.itemDescription.text = current.item_category
    }

    internal fun setCartItems(myCartItems: List<MyCartItem>) {
        this.myCartItems = myCartItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = myCartItems.size
}