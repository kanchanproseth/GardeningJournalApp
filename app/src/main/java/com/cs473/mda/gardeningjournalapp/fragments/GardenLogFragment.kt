package com.cs473.mda.gardeningjournalapp.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs473.mda.gardeningjournalapp.databinding.FragmentGardenLogBinding
import com.cs473.mda.gardeningjournalapp.viewmodels.GardenLogViewModel
import com.cs473.mda.gardeningjournalapp.fragments.adapter.GardenRecyclerViewAdapter
import com.cs473.mda.gardeningjournalapp.repository.PlantRepository


class GardenLogFragment : BaseFragment() {

    private lateinit var viewModel: GardenLogViewModel
    private lateinit var binding: FragmentGardenLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.addButton.setOnClickListener {
            val direction =
                GardenLogFragmentDirections.actionLogFragmentToAddPlantFragment(
                    "Add New Plant"
                )
            findNavController().navigate(direction)
        }

        viewModel.allPlants.observe(viewLifecycleOwner) {

            viewModel.mock()

            binding.recyclerView.adapter = GardenRecyclerViewAdapter(it, onItemClick = { plantId ->
                val direction =
                    GardenLogFragmentDirections.actionLogFragmentToPlantDetailFragment()
                direction.plantId = plantId
                findNavController().navigate(direction)
            })

        }
    }
}