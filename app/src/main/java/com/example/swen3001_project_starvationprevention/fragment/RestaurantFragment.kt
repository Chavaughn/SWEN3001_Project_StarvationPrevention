package com.example.swen3001_project_starvationprevention.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.adapter.ItemListAdapter
import com.example.swen3001_project_starvationprevention.databinding.RestaurantFragmentBinding
import com.example.swen3001_project_starvationprevention.model.RestaurantItemViewModel
import com.example.swen3001_project_starvationprevention.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RestaurantFragment : Fragment() {



    private var _binding: RestaurantFragmentBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>? = null
    private lateinit var rViewModel: RestaurantItemViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RestaurantFragmentBinding.inflate(inflater, container, false)

        val recyclerView = _binding!!.recyclerView
        layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        adapter = ItemListAdapter()
        recyclerView.adapter = adapter

        rViewModel = ViewModelProvider(this)[RestaurantItemViewModel::class.java]
        updateRestaurantWithDatabase(adapter as ItemListAdapter)


        //Create Button
        var button = _binding!!.button

        button.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantFragment_to_homeFragment)
            //Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
        //Create Clickable text
        var Breakfastbtn = _binding!!.textViewBreakfast
        Breakfastbtn.setOnClickListener {
            Toast.makeText(context, "Breakfast Clicked", Toast.LENGTH_SHORT).show()
            updateFilteredItemsWithDatabase(adapter as ItemListAdapter, "Mains")
        }

        var Lunchbtn = _binding!!.textViewLunch
        Lunchbtn.setOnClickListener {
            Toast.makeText(context, "Lunch Clicked", Toast.LENGTH_SHORT).show()
            updateFilteredItemsWithDatabase(adapter as ItemListAdapter, "Sides")
        }

        var Dinnerbtn = _binding!!.textViewDinner
        Dinnerbtn.setOnClickListener {
            Toast.makeText(context, "Dinner Clicked", Toast.LENGTH_SHORT).show()
            updateFilteredItemsWithDatabase(adapter as ItemListAdapter, "Drinks")
        }



        return binding.root

    }
    private fun updateRestaurantWithDatabase(adapter: ItemListAdapter) {

        rViewModel.allItems.observe(viewLifecycleOwner, Observer { items ->
            items?.let { adapter.setData(it) }
        })
    }

    private fun updateFilteredItemsWithDatabase(adapter: ItemListAdapter, category: String) {
        rViewModel.getFilteredItems(category).observe(viewLifecycleOwner, Observer { items ->
            items?.let { adapter.setData(it) }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun navigateToItemFragment() {
        findNavController().navigate(R.id.action_restaurantFragment_to_itemFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}