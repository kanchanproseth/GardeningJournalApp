package com.cs473.mda.gardeningjournalapp.viewmodels
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.cs473.mda.gardeningjournalapp.entity.Plant
import com.cs473.mda.gardeningjournalapp.repository.PlantRepository
import kotlinx.coroutines.launch

class GardenLogViewModel(app: Application): AndroidViewModel(app) {
    private val repository: PlantRepository
    lateinit var allPlants: LiveData<List<Plant>>

    init {
        repository = PlantRepository(app)
        viewModelScope.launch {
            allPlants = repository.allPlants
        }
    }

    fun getPlantById(plantId: Int) = repository.getPlantById(plantId)

    private fun getPlants(): Any {
        return viewModelScope.launch {
            repository.allPlants
        }
    }

    fun mock() {
        if ((allPlants.value?.size ?: 0) > 0) {
            return
        }

        val plants = mutableListOf<Plant>()
        plants.add(Plant(name = "Rose", type = "Flower", wateringFrequency = 2, plantingDate = "2023-01-01"))
        plants.add(Plant(name = "Tomato", type = "Vegetable", wateringFrequency = 3, plantingDate = "2023-02-15"))
        plants.add(Plant(name = "Basil", type = "Herb", wateringFrequency = 1, plantingDate = "2023-03-10"))
        plants.forEach {
            viewModelScope.launch {
                repository.insert(it)
            }
        }

        allPlants = repository.allPlants
    }

    fun deletePlantById(id: Int) = viewModelScope.launch { repository.delete(id) }
}