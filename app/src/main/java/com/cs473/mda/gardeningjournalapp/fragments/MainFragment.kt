package com.cs473.mda.gardeningjournalapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cs473.mda.gardeningjournalapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            button.setOnClickListener {
                val direction =
                    MainFragmentDirections.actionHomeFragmentToLogFragment()
                findNavController().navigate(direction)
            }
        }

    }
}