package com.example.appsaludmi.db.model

data class PerfilUsuario (

    var usuario : String,

    var nombre_apellido: String,

    var domicilio: String,

    var latlng: String,

    var fechaNacimiento: String,

    var fechaConcepcion: String,

    var personasHogar: Long,

    var habitaciones: Long,

    var calefaccion: String,

    var fumadora: Boolean,

    var conviveFumadores: Boolean
 )