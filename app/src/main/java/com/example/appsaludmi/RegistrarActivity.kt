package com.example.appsaludmiimport androidx.appcompat.app.AppCompatActivityimport android.os.Bundleimport com.example.appsaludmi.databinding.ActivityRegistrarBindingclass RegistrarActivity : AppCompatActivity() {    private lateinit var binding: ActivityRegistrarBinding    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        binding = ActivityRegistrarBinding.inflate( layoutInflater )        setContentView( binding.root )    }}