package com.cs473.mda.gardeningjournalapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cs473.mda.gardeningjournalapp.R
import com.cs473.mda.gardeningjournalapp.databinding.FragmentPlantDetailsBinding
import com.cs473.mda.gardeningjournalapp.entity.Plant
import com.cs473.mda.gardeningjournalapp.viewmodels.GardenLogViewModel
import kotlinx.coroutines.launch

class PlantDetailsFragment : BaseFragment() {

    private var plantId: Int = 0
    private lateinit var viewModel: GardenLogViewModel

    lateinit var binding: FragmentPlantDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            plantId = it.getInt("plantId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlantDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the plantId from the arguments
        plantId = arguments?.getInt("plantId") ?: 0
        viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)

        // Observe the plant details and update UI
        launch {
            viewModel.getPlantById(plantId).observe(viewLifecycleOwner) { plant ->
                plant?.let { displayPlantDetails(it) }
            }
        }

        // Delete button
        binding.deleteButton.setOnClickListener {
            showDeleteConfirmation()
        }

        // Update button
        binding.editButton.setOnClickListener {
            val direction =
                PlantDetailsFragmentDirections.actionPlantDetailFragmentToAddPlantFragment(
                    "Edit Plant"
                )
            direction.editPlantId = plantId
            findNavController().navigate(direction)
        }
    }

    private fun showDeleteConfirmation() {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Are you sure want to delete this plant?")
        dialog.setPositiveButton("Yes") { _, _ ->
            viewModel.deletePlantById(plantId)
            // pop back
            findNavController().popBackStack()
        }
        dialog.setNegativeButton("No") { _, _ ->

        }
        dialog.show()
    }


    private fun displayPlantDetails(plant: Plant) {
        // Update UI with plant details
        binding.plantNameTextView.text = plant.name
        binding.plantTypeTextView.text = "Type: ${plant.type}"
        binding.wateringFrequencyTextView.text = "Watering Frequency: ${plant.wateringFrequency} days"
        binding.plantingDateTextView.text = "Planting Date: ${plant.plantingDate}"
    }
}
