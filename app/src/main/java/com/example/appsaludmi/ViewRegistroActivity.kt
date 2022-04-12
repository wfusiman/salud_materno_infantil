package com.example.appsaludmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appsaludmi.databinding.ActivityViewRegistroBinding

class ViewRegistroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewRegistroBinding
    private lateinit var model: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewRegistroBinding.inflate( layoutInflater )
        setContentView( binding.root )

        binding.textViewNombreValue.text = intent.getStringExtra( EXTRA_MESSAGE_1 )
        binding.textViewApellidoValue.text = intent.getStringExtra( EXTRA_MESSAGE_2)
        binding.textViewDomicilioValue.text = intent.getStringExtra( EXTRA_MESSAGE_3 )
        binding.textViewFNacValue.text = intent.getStringExtra( EXTRA_MESSAGE_4)
    }
}