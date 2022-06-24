package com.example.appsaludmi.db.model

data class Farmacia (
    var nombre: String,
    var lat: String,
    var lng: String,
    var turnos: ArrayList<String>
)