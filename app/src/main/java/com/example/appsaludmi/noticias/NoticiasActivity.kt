package com.example.appsaludmi.noticias

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appsaludmi.R
import com.example.appsaludmi.databinding.ActivityNoticiasBinding

class NoticiasActivity : AppCompatActivity(), CoordinadoraNoticias {

    private lateinit var binding: ActivityNoticiasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onChangeNoticia( index: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_contenido) as ContenidoFragment
        fragment.cambiarNoticia(index)
    }
}