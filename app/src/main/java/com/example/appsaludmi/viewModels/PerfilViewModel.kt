package com.example.appsaludmi.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appsaludmi.db.connect.SmiRoomDb
import com.example.appsaludmi.db.model.Perfil
import com.example.appsaludmi.db.repo.RepoPerfiles

class PerfilViewModel( application: Application): AndroidViewModel( application ) {
    
    private val repository: RepoPerfiles
    val allPerfiles: LiveData<List<Perfil>>

    init {
        val perfilDao = SmiRoomDb.getDb( application, viewModelScope ).perfilDao()
        repository = RepoPerfiles( perfilDao )
        allPerfiles = repository.allPerfiles
    }
}