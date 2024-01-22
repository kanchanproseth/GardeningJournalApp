package com.cs473.mda.gardeningjournalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.cs473.mda.gardeningjournalapp.dao.PlantDao
import com.cs473.mda.gardeningjournalapp.database.PlantDatabase
import com.cs473.mda.gardeningjournalapp.entity.Plant

class PlantRepository(application: Application) {
    private val plantDao: PlantDao
    val allPlants: LiveData<List<Plant>>

    init {
        val database = PlantDatabase.getDatabase(application)
        plantDao = database.plantDao()
        allPlants = plantDao.getAllPlants()
    }

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(id: Int) {
        plantDao.delete(id)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantById(plantId)
    }
}
