package com.example.swen3001_project_starvationprevention

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.swen3001_project_starvationprevention.databinding.HomeFragmentBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(fragView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragView, savedInstanceState)
        binding.viewAllRes.setOnClickListener {
            returnMaintSnackbar(fragView,"We haven't done anything to this yet!")
        }
        binding.search.setOnClickListener{
            returnMaintSnackbar(fragView,"We haven't done anything to this yet!")
        }
        binding.restaurant1.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(this@HomeFragment.id, RestaurantFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
    }

    private fun returnMaintSnackbar(view: View, text: String){
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackbar.setAction("Action", null).show()
        snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity().applicationContext, R.color.appMain))
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}