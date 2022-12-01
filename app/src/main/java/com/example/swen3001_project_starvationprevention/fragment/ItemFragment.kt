package com.example.swen3001_project_starvationprevention.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.databinding.ItemFragmentBinding
import com.example.swen3001_project_starvationprevention.model.FavouriteItems
import com.example.swen3001_project_starvationprevention.model.MyCartItem
import com.example.swen3001_project_starvationprevention.model.viewmodel.FavouritesViewModel
import com.example.swen3001_project_starvationprevention.model.viewmodel.MyCartViewModel
import java.util.*

class ItemFragment : Fragment() {

    private val args by navArgs<ItemFragmentArgs>()
    private var _binding: ItemFragmentBinding? = null
    private lateinit var cartViewModel: MyCartViewModel
    private lateinit var favouritesViewModel: FavouritesViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        _binding = ItemFragmentBinding.inflate(inflater, container, false)
        cartViewModel = ViewModelProvider(this)[MyCartViewModel::class.java]
        favouritesViewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]

        var itemName = _binding?.itemName
        itemName?.text = (args.currentItem.item_name)

        var itemPrice = _binding?.FoodPrice
        itemPrice?.text = (args.currentItem.item_price.toString())

        var itemCategory = _binding?.itemCategory
        itemCategory?.text = (args.currentItem.item_category)

        //binding.itemDescription.text = "This is a test"
        

        //Button Creations and Navigation
        var Incrementbutton = _binding!!.increment
        var Decrementbutton = _binding!!.decrement
        var itemQuantity = _binding!!.quantityDisplay
        var backButton = _binding!!.backButton
        var favButton = _binding!!.favButton
        var addToCart = _binding!!.addToCart

        //Increment Item cost per click
        Incrementbutton.setOnClickListener {
            //Increment item quantity per click
            //findNavController().navigate(R.id.action_itemFragment_to_restaurantFragment)
            var quantity = itemQuantity.text.toString().toInt()
            quantity++
            itemQuantity.text = quantity.toString()
            //Increment item cost per click
            var cost = _binding!!.totalval.text.toString().toDouble()
            cost += args.currentItem.item_price
            _binding!!.totalval.text = cost.toString()
            //toast to say cost incremented by item price

        }
        //Decrement Item cost per click
        Decrementbutton.setOnClickListener {
            //Decrement item quantity per click
            var quantity = itemQuantity.text.toString().toInt()
            if (quantity > 0) {
                quantity--
                itemQuantity.text = quantity.toString()
                //Decrement item cost per click
                var cost = _binding!!.totalval.text.toString().toDouble()
                cost -= args.currentItem.item_price
                _binding!!.totalval.text = cost.toString()
                //toast to say cost decremented by item price
            }

        }

        addToCart.setOnClickListener{
            Toast.makeText(context, "Added ${itemQuantity.text}  ${itemName?.text} to cart", Toast.LENGTH_SHORT).show()
            var cost = _binding!!.totalval.text.toString().toDouble()
            val cartItem = MyCartItem(
                itemName?.text as String, itemCategory?.text as String, cost,
                Math.random().toLong(), UUID.randomUUID(), 1, (itemQuantity.text as String).toInt())
            cartViewModel.insert(cartItem)
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_itemFragment_to_restaurantFragment)
        }

        favButton.setOnClickListener {
            Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show()
            val favourite = FavouriteItems(
                itemName?.text as String, itemCategory?.text as String, (itemPrice?.text as String).toDouble(),
                Math.random().toLong(), UUID.randomUUID(), 1)
            favouritesViewModel.addItem(favourite)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}