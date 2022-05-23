package com.example.appsaludmi.db.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appsaludmi.db.dao.PerfilDAO
import com.example.appsaludmi.db.model.Perfil

class RepoPerfiles( private val perfilDao: PerfilDAO) {

    val allPerfiles: LiveData<List<Perfil>> = perfilDao.getPerfiles()
    
    suspend fun insert( perfil: Perfil ) {
        perfilDao.savePerfil( perfil )
    }

    suspend fun update(id: Int, nombre: String, apelido: String, domicilio: String, fnac: String ) {
        perfilDao.updatePerfil( id,nombre,apelido,domicilio,fnac)
    }

}