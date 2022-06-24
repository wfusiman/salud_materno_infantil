package com.example.appsaludmi.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appsaludmi.db.connect.SmiRoomDb
import com.example.appsaludmi.db.model.Perfil
import com.example.appsaludmi.db.repo.RepoPerfiles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilViewModel( application: Application): AndroidViewModel( application ) {
    
    private val repo: RepoPerfiles
    val perfiles: LiveData<List<Perfil>>


    init {
        val perfilDao =SmiRoomDb.obtenerDB( application, viewModelScope ).perfilDao()
        repo = RepoPerfiles(perfilDao)
        perfiles = repo.allPerfiles
    }


    fun registrar( perfil: Perfil) = viewModelScope.launch( Dispatchers.IO ) {
        repo.insert( perfil )
    }


    fun isUsuarioRegistrado( usuario: String ): Boolean {
        val list : List<Perfil>? = perfiles.value

        val perfil = list?.find { p -> p.usr == usuario }
        return perfil != null
    }

    fun getPerfil( usuario: String ): Perfil? {
        val list : List<Perfil>? = perfiles.value
        val perfil = list?.find { p -> p.usr == usuario }
        return perfil
    }

    fun validateUsuario( usr: String , passwd: String ): Perfil? {
        val list : List<Perfil>? = perfiles.value

        val perfil2 = list?.find { p -> p.usr == usr && p.passwd == passwd }
        if (perfil2 == null)
            return null;
        else {
            Log.i("validate","+++++ Perfil: " + perfil2!!.apellido.toString() )
            return perfil2
        }
    }

    fun updatePerfil()  = viewModelScope.launch( Dispatchers.IO ) {
    }
}