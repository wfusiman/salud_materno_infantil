package com.example.appsaludmi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class InfoViewModel(private val state: SavedStateHandle): ViewModel() {

    private val _id = MutableLiveData<Int> (0)
    val id: LiveData<Int> = _id

    private val _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> = _nombre

    private val _apellido = MutableLiveData<String>("")
    val apellido: LiveData<String> = _apellido

    private val _domicilio = MutableLiveData<String>("")
    val domicilio: LiveData<String> = _domicilio

    private val _fechanac = MutableLiveData<String>("")
    val fechaNacimiento: LiveData<String> = _fechanac

    private val _fuma = MutableLiveData<Boolean>(false)
    val fuma: LiveData<Boolean> = _fuma

    private val _fumaConvive = MutableLiveData<Boolean>(false)
    val fumaConvive: LiveData<Boolean> = _fumaConvive

    private val _personas = MutableLiveData<Int>(2)
    val personas: LiveData<Int> = _personas

    private val _habitaciones = MutableLiveData<Int>(3)
    val habitaciones: LiveData<Int> = _habitaciones

    private val _calefaccion = MutableLiveData<String>("Gas")
    val calefaccion: LiveData<String> = _calefaccion

    init {
        _nombre.value = state.get( "id")
        _apellido.value = state.get("apellido")
        _nombre.value = state.get("nombre")
        _domicilio.value = state.get("domicilio")
        _fechanac.value = state.get("fechanacimiento")
        _fuma.value = state.get("fuma")
        _fumaConvive.value = state.get("fumaConvive")
        _fechanac.value = state.get("personas")
        _fuma.value = state.get("habitaciones")
        _fumaConvive.value = state.get("calefaccion")
    }

    fun setId( i: Int ) {
        _id.value = i
    }

    fun setNombre( n: String){
        _nombre.value = n
    }

    fun setApellido( a: String ) {
        _apellido.value = a
    }

    fun setDomicilio( d: String ) {
        _domicilio.value = d
    }

    fun setFechaNac( f: String) {
        _fechanac.value = f
    }

    fun setFuma( f: Boolean) {
        _fuma.value = f
    }

    fun setFumaConvive( f: Boolean) {
        _fumaConvive.value = f
    }

    fun setPersonas( p: Int ) {
        _personas.value = p
    }

    fun setHabitaciones( h: Int ) {
        _habitaciones.value = h
    }

    fun setCalefaccion( c: String ) {
        _calefaccion.value = c
    }
}