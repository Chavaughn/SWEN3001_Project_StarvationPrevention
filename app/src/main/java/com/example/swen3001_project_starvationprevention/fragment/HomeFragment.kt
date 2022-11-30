package com.example.swen3001_project_starvationprevention.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.adapter.CartItemsAdapter
import com.example.swen3001_project_starvationprevention.adapter.HomeRestaurantsAdapter
import com.example.swen3001_project_starvationprevention.adapter.ItemListAdapter
import com.example.swen3001_project_starvationprevention.databinding.HomeFragmentBinding
import com.example.swen3001_project_starvationprevention.model.viewmodel.MyCartViewModel
import com.example.swen3001_project_starvationprevention.model.viewmodel.RestaurantItemViewModel
import com.example.swen3001_project_starvationprevention.model.viewmodel.RestaurantsHomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var homeRestaurantsAdapter: RecyclerView.Adapter<HomeRestaurantsAdapter.RestaurantsViewHolder>? = null
    private lateinit var rHViewModel: RestaurantsHomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)

        val recyclerView = _binding!!.homeRecyclerView
        layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        rHViewModel = ViewModelProvider(this)[RestaurantsHomeViewModel::class.java]
        val adapter = HomeRestaurantsAdapter(this.context, ViewModelProvider(this)[MyCartViewModel::class.java])
        homeRestaurantsAdapter = adapter
        recyclerView.adapter = homeRestaurantsAdapter
        updateRestaurantsWithDatabase(adapter)

        return binding.root

    }

    override fun onViewCreated(fragView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragView, savedInstanceState)
        binding.viewAllRes.setOnClickListener {
            Toast.makeText(fragView.context, "We don't have that many restaurants!", Toast.LENGTH_SHORT).show()
        }
        binding.search.setOnClickListener{
            Toast.makeText(fragView.context, "We don't have that many restaurants!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateRestaurantsWithDatabase(adapter: HomeRestaurantsAdapter) = runBlocking{
        rHViewModel.allRestaurantData.observe(viewLifecycleOwner, Observer{ items ->
            items?.let { adapter.setRestaurantsHome(it) }
        })

    }

    private fun returnMaintSnackbar(view: View, text: String){
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackbar.setAction("Action", null).show()
        snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity().applicationContext,
            R.color.appMain
        ))
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}