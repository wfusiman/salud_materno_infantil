package com.example.appsaludmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.example.appsaludmi.databinding.ActivityInitBinding
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class InitActivity : AppCompatActivity() {

    private var _binding: ActivityInitBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInitBinding.inflate( layoutInflater )
        val view = binding.root
        setContentView( view )

        /*user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val principalAct = Intent( this, MainActivity::class.java )
            startActivity( principalAct )
        }*/
        supportActionBar?.hide()
    }
}