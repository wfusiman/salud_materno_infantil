package com.example.appsaludmi.viewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class PerfilUsuarioViewModel: ViewModel() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val fdb: FirebaseDatabase = FirebaseDatabase.getInstance()

    private var uuid: String = ""

    private val _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> = _nombre

    private val _domicilio = MutableLiveData<String>("")
    val domicilio: LiveData<String> = _domicilio

    private val _latlng = MutableLiveData<String>("")
    val latlng: LiveData<String> = _latlng

    private val _fechanac = MutableLiveData<String>("")
    val fechaNacimiento: LiveData<String> = _fechanac

    private val _fechaconcepcion = MutableLiveData<String>("")
    val fechaConcepcion: LiveData<String> = _fechaconcepcion

    private val _fuma = MutableLiveData<Boolean>(false)
    val fuma: LiveData<Boolean> = _fuma

    private val _fumaConvive = MutableLiveData<Boolean>(false)
    val fumaConvive: LiveData<Boolean> = _fumaConvive

    private val _personas = MutableLiveData<Long>(2)
    val personas: LiveData<Long> = _personas

    private val _habitaciones = MutableLiveData<Long>(3)
    val habitaciones: LiveData<Long> = _habitaciones

    private val _calefaccion = MutableLiveData<String>("Gas")
    val calefaccion: LiveData<String> = _calefaccion

    init {
        val user = auth.currentUser
        val ref = fdb.reference
        if (uuid.isEmpty() && user != null) {
            Log.i("viewModel","Cargar info")
            ref.child("perfiles").child(user.uid).get().addOnSuccessListener {
                uuid = user.uid
                _nombre.value = it.child("nombre_apellido").value.toString()
                _domicilio.value = it.child("domicilio").value.toString()
                _latlng.value = it.child("latlng").value.toString()
                _fechanac.value = it.child("fechaNacimiento").value.toString()
                _fechaconcepcion.value = it.child("fechaConcepcion").value.toString()
                _fuma.value = it.child("fumadora").value as Boolean
                _fumaConvive.value = it.child("conviveFumadores").value as Boolean
                _personas.value = it.child("personasHogar").value as Long
                _habitaciones.value = it.child("habitaciones").value as Long
                _calefaccion.value = it.child("calefaccion").value.toString()

            }.addOnFailureListener {
                Log.e("SMI initViewModel", "Error getting data ")
            }
        }
        else
            Log.i("viewModel","Info ya cargada, uid: " + uuid )
    }


    fun updateHogarInfo() {

    }
    fun updateTabaquismoInfo() {

    }

    fun setNombre( n: String){
        _nombre.value = n
    }

    fun setDomicilio( d: String ) {
        _domicilio.value = d
    }

    fun setLatlng( ll: String ) {
        _latlng.value = ll
    }

    fun setFechaNac( f: String) {
        _fechanac.value = f
    }

    fun setFechaConcepcion( f: String ) {
        _fechaconcepcion.value = f
    }

    fun setFuma( f: Boolean) {
        _fuma.value = f
    }

    fun setFumaConvive( f: Boolean) {
        _fumaConvive.value = f
    }

    fun setPersonas( p: Long ) {
        _personas.value = p
    }

    fun setHabitaciones( h: Long ) {
        _habitaciones.value = h
    }

    fun setCalefaccion( c: String ) {
        _calefaccion.value = c
    }
}