package com.example.swen3001_project_starvationprevention

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.adapter.CartItemsAdapter
import com.example.swen3001_project_starvationprevention.databinding.ActivityHomeBinding
import com.example.swen3001_project_starvationprevention.model.MyCartViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var myCartItemViewModel: MyCartViewModel
    private val newCartItemActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.main_Activity_Fragment) as NavHostFragment).navController
        setupWithNavController(binding.bottomNavigationView, navController)

        val cartItemListView = findViewById<RecyclerView>(R.id.recyclerview)
        val cartItemAdapter = CartItemsAdapter(this)
        cartItemListView.adapter = cartItemAdapter
        cartItemListView.layoutManager = LinearLayoutManager(this)

        myCartItemViewModel = ViewModelProvider(this)[myCartItemViewModel::class.java]

        myCartItemViewModel.allCartItems.observe(this, Observer{ items ->
            items?.let { cartItemAdapter.setCartItems(it) }
        })

    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_home)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}