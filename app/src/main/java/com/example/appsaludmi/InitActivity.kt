package com.example.appsaludmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsaludmi.databinding.ActivityInitBinding

class InitActivity : AppCompatActivity() {

    private var _binding: ActivityInitBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_init)
        _binding = ActivityInitBinding.inflate( layoutInflater )
        val view = binding.root
        setContentView( view )
        getSupportActionBar()?.hide()
    }
}