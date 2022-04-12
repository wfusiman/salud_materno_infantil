package com.example.appsaludmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appsaludmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root )

        binding.btnRegistrarse.setOnClickListener { actionRegistrarse() }
    }

    private fun actionRegistrarse() {
        val msj = "REGISTRAR"
        val intent = Intent( this, RegistrarActivity::class.java).apply { }
        startActivity( intent )
    }
}