package com.example.lokakuis.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.lokakuis.R
import com.example.lokakuis.base.extensions.setVisibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bnvMain: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = findNavController(R.id.nav_host_fragment)

        val topLevelDestinationIds = setOf(
            R.id.feedFragment,
        )

        bnvMain = findViewById(R.id.bnv_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bnvMain.setVisibleOrGone(topLevelDestinationIds.contains(destination.id))
        }
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onSupportNavigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}