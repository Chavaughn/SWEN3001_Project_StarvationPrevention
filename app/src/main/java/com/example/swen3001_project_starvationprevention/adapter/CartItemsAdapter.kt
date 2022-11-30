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
import com.example.swen3001_project_starvationprevention.model.viewmodel.MyCartViewModel
import java.util.*

class CartItemsAdapter internal constructor(context: Context?, myCartViewModel: MyCartViewModel?): RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var myCartItems = emptyList<MyCartItem>() // Cached copy of Students
    private val cartViewModel = myCartViewModel

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemNameCart)
        val itemPrice: TextView = itemView.findViewById(R.id.itemCostCart)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescriptionCart)
        val itemQuantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val deleteCartItem: Button = itemView.findViewById(R.id.deleteCartItem)


        init{

            deleteCartItem.setOnClickListener{
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "Removed from cart!", Toast.LENGTH_SHORT).show()
                val cartItem = MyCartItem(myCartItems[position].item_name,myCartItems[position].item_category,myCartItems[position].item_price,myCartItems[position].item_id,
                    UUID.randomUUID(),myCartItems[position].restaurant_id, 1)
                cartViewModel?.remove(cartItem)
            }

        }

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
        holder.itemQuantity.text = current.quantity.toString()
    }

    internal fun setCartItems(myCartItems: List<MyCartItem>) {
        this.myCartItems = myCartItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = myCartItems.size
}