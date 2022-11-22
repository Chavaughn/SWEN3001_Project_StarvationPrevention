package com.example.swen3001_project_starvationprevention.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.adapter.CartItemsAdapter
import com.example.swen3001_project_starvationprevention.databinding.MyCartFragmentBinding
import com.example.swen3001_project_starvationprevention.model.MyCartViewModel
import kotlinx.coroutines.runBlocking

class MyCartFragment : Fragment() {

    private var _binding: MyCartFragmentBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var cartItemAdapter: RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder>? = null

    private lateinit var myCartItemViewModel: MyCartViewModel
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MyCartFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MyCartFragmentBinding.inflate(inflater, container, false)

        val recyclerView = _binding!!.recyclerview
        layoutManager = LinearLayoutManager(this.context)

        recyclerView.layoutManager = layoutManager
        val adapter = CartItemsAdapter(this.context)
        cartItemAdapter = adapter
        recyclerView.adapter = cartItemAdapter
        myCartItemViewModel = ViewModelProvider(this)[MyCartViewModel::class.java]
        updateCartWithDatabase(adapter)



        return binding.root
    }
    override fun onViewCreated(fragView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragView, savedInstanceState)

        refreshFragment(this.context)

    }


    private fun updateCartWithDatabase(adapter: CartItemsAdapter) = runBlocking{
            myCartItemViewModel.allCartItems.observe(viewLifecycleOwner, Observer{ items ->
                items?.let { adapter.setCartItems(it) }
            })

    }

    private fun refreshFragment(context: Context?) {
        context?.let {
            val fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(R.id.myCartFragment)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach((it))
                    fragmentTransaction.commit()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}