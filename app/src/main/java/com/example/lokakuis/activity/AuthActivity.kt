package com.example.lokakuis.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lokakuis.R
import com.google.android.material.appbar.MaterialToolbar

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val appActionBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(appActionBar)

        val navController: NavController = findNavController(R.id.nav_host_fragment)

        val topLevelDestinationIds = setOf(
            R.id.loginFragment,
            R.id.registerFragment,
        )

        val appBarConfiguration = AppBarConfiguration
            .Builder(topLevelDestinationIds)
            .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).popBackStack()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onSupportNavigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}