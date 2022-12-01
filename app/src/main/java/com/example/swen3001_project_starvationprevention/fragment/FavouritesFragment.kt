package com.example.swen3001_project_starvationprevention.fragment

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
import com.example.swen3001_project_starvationprevention.model.viewmodel.RestaurantItemViewModel
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.adapter.FavouriteAdapter
import com.example.swen3001_project_starvationprevention.databinding.FavouritesFragmentBinding
import com.example.swen3001_project_starvationprevention.model.viewmodel.FavouritesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavouritesFragment : Fragment() {



    private var _binding: FavouritesFragmentBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FavouriteAdapter.ViewHolder>? = null
    private lateinit var fViewModel: FavouritesViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(this.context)
        val recyclerView = _binding!!.favrecyclerview
        recyclerView.layoutManager = layoutManager
        fViewModel = ViewModelProvider(this)[FavouritesViewModel::class.java]
        adapter = FavouriteAdapter(this.context, fViewModel)
        recyclerView.adapter = adapter

        updatefavouriteWithDatabase(adapter as FavouriteAdapter)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updatefavouriteWithDatabase(adapter: FavouriteAdapter) {
        fViewModel.allItems.observe(viewLifecycleOwner, Observer { items ->
            items?.let { adapter.setData(it) }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}