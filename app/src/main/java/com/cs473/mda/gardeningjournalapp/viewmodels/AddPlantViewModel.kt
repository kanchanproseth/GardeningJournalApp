package com.cs473.mda.gardeningjournalapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cs473.mda.gardeningjournalapp.entity.Plant
import com.cs473.mda.gardeningjournalapp.repository.PlantRepository
import kotlinx.coroutines.launch

class AddPlantViewModel(app: Application): AndroidViewModel(app) {
    private val repository: PlantRepository

    var plants: LiveData<Plant>? = null
    val addSuccess = MutableLiveData<Boolean>()

    init {
        repository = PlantRepository(app)
    }

    fun addPlantOrUpdatePlan(
        name: String,
        type: String,
        watering: Int,
        plantDate: String) {

        val plant = plants?.value

        if (plant != null) {
            // Update existing plant
            plant.let {
                plant.name = name
                plant.type = type
                plant.wateringFrequency = watering
                plant.plantingDate = plantDate

                updatePlant(plant)
            }

        } else {
            // Insert new plant
            val plant = Plant(name = name, type = type, wateringFrequency = watering, plantingDate = plantDate)
            addPlant(plant)
        }
        // Notify update change
        addSuccess.value = true
    }

    fun getEditingPlant(plantId: Int) {
        viewModelScope.launch {
            plants = repository.getPlantById(plantId)
        }
    }
    private fun addPlant(plant: Plant) = viewModelScope.launch { repository.insert(plant) }
    private fun updatePlant(plant: Plant) = viewModelScope.launch { repository.update(plant) }
}