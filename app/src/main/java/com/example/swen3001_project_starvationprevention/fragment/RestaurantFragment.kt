package com.example.swen3001_project_starvationprevention.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.databinding.RestaurantFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RestaurantFragment : Fragment() {



    private var _binding: RestaurantFragmentBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>? = null

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


        //Create Button
        var button = _binding!!.button

        button.setOnClickListener {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
        //Create Clickable text
        var Breakfastbtn = _binding!!.textViewBreakfast
        Breakfastbtn.setOnClickListener {
            Toast.makeText(context, "Breakfast Clicked", Toast.LENGTH_SHORT).show()
        }

        var Lunchbtn = _binding!!.textViewLunch
        Lunchbtn.setOnClickListener {
            Toast.makeText(context, "Lunch Clicked", Toast.LENGTH_SHORT).show()
        }

        var Dinnerbtn = _binding!!.textViewDinner
        Dinnerbtn.setOnClickListener {
            Toast.makeText(context, "Dinner Clicked", Toast.LENGTH_SHORT).show()
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