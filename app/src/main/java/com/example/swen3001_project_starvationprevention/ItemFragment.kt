package com.example.swen3001_project_starvationprevention

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.swen3001_project_starvationprevention.databinding.ItemFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ItemFragment : Fragment() {


    private var _binding: ItemFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TextView itemDescription = (TextView)findViewById(R.id.itemDescription);
        super.onCreate(savedInstanceState)
        _binding = ItemFragmentBinding.inflate(inflater, container, false)
        var itemDescription = _binding?.itemDescription;
        itemDescription?.text = "This is a test description"
        //binding.itemDescription.text = "This is a test"
        

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