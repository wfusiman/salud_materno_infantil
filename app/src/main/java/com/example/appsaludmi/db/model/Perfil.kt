package com.example.appsaludmi.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="perfiles")
data class Perfil (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "nombre")
    var nombre: String,

    @ColumnInfo(name = "apellido")
    var apellido: String,

    @ColumnInfo(name = "domicilio")
    var domicilio: String?,

    @ColumnInfo(name = "fecha_nacimiento")
    var fechaNacicimiento: String
)