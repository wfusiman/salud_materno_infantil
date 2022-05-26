package com.example.appsaludmi.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appsaludmi.db.connect.SmiRoomDb
import com.example.appsaludmi.db.model.Perfil
import com.example.appsaludmi.db.repo.RepoPerfiles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PerfilViewModel( application: Application): AndroidViewModel( application ) {
    
    private val repo: RepoPerfiles
    val perfiles: LiveData<List<Perfil>>
    private var perfilSign : Perfil? = null // perfil de usuario logueado

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

    fun validateUsuario( usr: String , passwd: String ): Boolean {
        val list : List<Perfil>? = perfiles.value

        val perfil = list?.find { p -> p.usr == usr && p.passwd == passwd }
        if (perfil == null)
            return false;
        else {
            perfilSign = perfil
            return true
        }
    }

    fun updatePerfil( id: Int, perfil: Perfil )  = viewModelScope.launch( Dispatchers.IO ) {
        repo.update( id, perfil.nombre, perfil.apellido, perfil.domicilio, perfil.fechaNacimiento)
    }
}