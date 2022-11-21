package com.example.swen3001_project_starvationprevention.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swen3001_project_starvationprevention.R
import com.example.swen3001_project_starvationprevention.databinding.HomeFragmentBinding
import com.example.swen3001_project_starvationprevention.databinding.MyCartFragmentBinding
import com.example.swen3001_project_starvationprevention.model.MyCartViewModel

class MyCartFragment : Fragment() {

    private var _binding: MyCartFragmentBinding? = null

    companion object {
        fun newInstance() = MyCartFragment()
    }

    private lateinit var viewModel: MyCartViewModel
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MyCartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(fragView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragView, savedInstanceState)
        viewModel = ViewModelProvider(this)[MyCartViewModel::class.java]
        // TODO: Use the ViewModel

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}