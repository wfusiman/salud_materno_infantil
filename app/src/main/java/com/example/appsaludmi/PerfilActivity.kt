package com.example.appsaludmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.appsaludmi.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private var _binding: ActivityPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_perfil)
        _binding = ActivityPerfilBinding.inflate( layoutInflater )
        val view = binding.root

        setContentView( view )
    }
}