package com.example.appsaludmi.recomendaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsaludmi.databinding.ActivityRecomendacionesBinding

class RecomendacionesActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityRecomendacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecomendacionesBinding.inflate( layoutInflater )
        setContentView( _binding.root )
    }
}