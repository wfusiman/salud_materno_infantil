package com.example.appsaludmi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsaludmi.db.connect.SmiRoomDb
import com.example.appsaludmi.db.connect.SmiRoomDb_Impl
import com.example.appsaludmi.db.dao.PerfilDAO
import com.example.appsaludmi.db.repo.RepoPerfiles

class RegistroViewModel: ViewModel() {

    private var _nombre = MutableLiveData<String>("")
    val nombre: LiveData<String> get() = _nombre

    private var _apellido = MutableLiveData<String>("")
    val apellido: LiveData<String> get() = _apellido

    private var _domicilio = MutableLiveData<String>("")
    val domicilio: LiveData<String> get() = _domicilio

    private var _fecha_nacimiento = MutableLiveData<String>("")
    val fnac: LiveData<String> get() = _fecha_nacimiento


    public fun registry( nombre: String, apellido: String, domicilio: String, fnac: String ) {
        Log.i( "info", "registry: " + nombre + " , " + apellido + ", " + domicilio + ", " + fnac)
        _nombre.value = nombre
        _apellido.value = apellido
        _domicilio.value = domicilio
        _fecha_nacimiento.value = fnac
    }

    public fun registryNombre( nom : String ) {
        _nombre.value = nom
    }

    public fun registryApellido( ap: String ) {
        _apellido.value = ap
    }

    public fun registruDomicilio( dom: String ) {
        _domicilio.value = dom
    }

    public fun registryFechaNacimiento( fn: String ) {
        _fecha_nacimiento.value = fn
    }


    /*public fun validateNombre(): Boolean {
        return  _nombre.value.length >= 3
    }

    public fun validateApellido() : Boolean {
        return _apellido.value.length >= 4
    }

    public fun validateDomicilio(): Boolean {
        return _domicilio.value.length >= 5
    }

    public fun validateFechaNacimiento(): Boolean {
        val dma = _fecha_nacimiento.value.split('/')
        if (dma.size != 3)
            return false
        val dia = dma[0].toInt()
        if (dia <= 0 && dia > 31)
            return false;
        val mes = dma[1].toInt()
        if (mes <=0 && mes > 12)
            return false
        val anio = dma[2].toInt()
        if (anio < 1950)
            return false
    }*/

}