package com.example.appsaludmi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appsaludmi.db.model.Perfil

@Dao
interface PerfilDAO {

    @Query("SELECT * FROM perfiles ORDER BY id DESC")
    fun getPerfiles(): LiveData<List<Perfil>>

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun savePerfil( perfil: Perfil)

    @Query("delete from perfiles")
    suspend fun  deleteAllPerfiles()

}