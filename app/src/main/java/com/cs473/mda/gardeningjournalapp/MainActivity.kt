package com.cs473.mda.gardeningjournalapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var configuration: AppBarConfiguration
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navigationController = navHostFragment.navController
//        configuration = AppBarConfiguration(navigationController.graph)
//        setupActionBarWithNavController(navigationController, configuration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp(configuration)
    }
}