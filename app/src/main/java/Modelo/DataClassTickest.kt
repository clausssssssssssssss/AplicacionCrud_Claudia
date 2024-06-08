package Modelo

data class DataClassTickest(
    val UUID: String,
    var ID_ticket: Int,
    val titulo: String,
    val descripcion: String,
    val Autor: String,
    val Email: String,
    val Creacion: String,
    val Estado: String,
    val Finalizacion: String
)
