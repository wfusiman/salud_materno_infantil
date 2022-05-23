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

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerfil( perfil: Perfil)

    @Query("Delete from perfiles")
    suspend fun  deleteAllPerfiles(): Int

    @Query("UPDATE perfiles SET nombre=:nom, apellido=:ap, domicilio=:dom, fecha_nacimiento=:fnac WHERE id = :idUsr")
    suspend fun updatePerfil(idUsr: Int, nom: String, ap: String, dom: String, fnac: String )

}