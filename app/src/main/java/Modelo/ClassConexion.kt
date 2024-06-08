package Modelo

import java.sql.Connection
import java.sql.Driver
import java.sql.DriverManager

class ClassConexion {

    fun cadenaConexion(): Connection? {

    try{
        val url = "jbdc:oracle:thin:@10.0.1.15:1521:xe"
        val usuario = "Claudia Hernandez"
        val contrasena = "2704023010060103"

    val conection = DriverManager.getConnection(url, usuario, contrasena)
    return conection
    }catch(e: Exception){
        println("Este es el error: $e")
        return  null
    }
  }
}