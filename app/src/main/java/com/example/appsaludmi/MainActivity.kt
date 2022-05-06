package com.example.appsaludmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.appsaludmi.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater )
        val view = binding.root

        drawerLayout = binding.drawerLayout
        val navHostFragmet = supportFragmentManager.findFragmentById( R.id.nav_host_fragment ) as NavHostFragment
        val navController = navHostFragmet.navController
        // findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        NavigationUI.setupWithNavController( binding.navView, navController )
        appBarConfig = AppBarConfiguration( navController.graph )
        NavigationUI.setupActionBarWithNavController( this, navController, drawerLayout )

        setContentView( view )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController( R.id.nav_host_fragment)
        return NavigationUI.navigateUp( navController, drawerLayout )
    }
}