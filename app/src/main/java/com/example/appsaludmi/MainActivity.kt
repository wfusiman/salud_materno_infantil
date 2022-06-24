package com.example.appsaludmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.appsaludmi.databinding.ActivityMainBinding
import com.example.appsaludmi.noticias.NoticiasActivity
import com.example.appsaludmi.recomendaciones.RecomendacionesActivity
import com.example.appsaludmi.viewModels.PerfilViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var perfilViewModel: PerfilViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater )
        val view = binding.root

        drawerLayout = binding.drawerLayout

        val navHostFragmet = supportFragmentManager.findFragmentById( R.id.nav_host_fragment ) as NavHostFragment
        navController = navHostFragmet.navController
        NavigationUI.setupWithNavController( binding.navView, navController )
        appBarConfig = AppBarConfiguration( navController.graph )
        NavigationUI.setupActionBarWithNavController( this, navController, drawerLayout )

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            Log.i("listener item ", " menu item " + menuItem )
            if (menuItem.toString() == "Noticias") {
                //navController.navigate( R.id.noticiasFragment )
                val  noticiasAct =  Intent( this, NoticiasActivity::class.java)
                startActivity( noticiasAct )
            }
            else if (menuItem.toString() == "Recomendaciones") {
                val  recsAct =  Intent( this, RecomendacionesActivity::class.java)
                startActivity( recsAct )
            }
            else if (menuItem.toString() == "Perfil") {
                val  perfilAct =  Intent( this, PerfilActivity::class.java)
                startActivity( perfilAct )
            }
            else if (menuItem.toString() == "Farmacias") {
                val  farmaciasAct =  Intent( this, FarmaciasMapsActivity::class.java)
                startActivity( farmaciasAct )
            }

            drawerLayout.closeDrawer( GravityCompat.START )
            return@setNavigationItemSelectedListener true
        }
        perfilViewModel = ViewModelProvider(this)[PerfilViewModel::class.java]

        setContentView( view )
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController( R.id.nav_host_fragment)
        return NavigationUI.navigateUp( navController, drawerLayout )
    }

    override fun onBackPressed() {
        return
    }
}
