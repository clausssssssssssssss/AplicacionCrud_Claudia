package claudia.ortega.aplicacioncrudclaudia

import Modelo.ClassConexion
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class activity_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCorreoLo = findViewById<EditText>(R.id.txtCorreoLO)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaLO)
        val btnIngresarLo = findViewById<Button>(R.id.btnIngresarLO)
        val btnRegristrate = findViewById<Button>(R.id.btnRegistrate)

        btnIngresarLo.setOnClickListener {
            val pantallaPrincipal = Intent(this, Bienvenido::class.java)
            GlobalScope.launch(Dispatchers.IO){
                val objConexion = ClassConexion().cadenaConexion()
                val comprobarUsuario = objConexion?.prepareStatement("SELECT * FROM TBusuariosh WHERE Email = ? AND Contrasena = ?")!!
                comprobarUsuario.setString(1, txtCorreoLo.text.toString())
                comprobarUsuario.setString(2, txtContrasena.text.toString())
                val resultado = comprobarUsuario.executeQuery()
                if (resultado.next()){
                    startActivity(pantallaPrincipal)
                }else{
                    println("Lo siento no encontramos tu usuario")
                }
            }
        }
        btnRegristrate.setOnClickListener {
            val pantallaRegistrate = Intent(this, activity_registrar::class.java)
            startActivity(pantallaRegistrate)
        }
    }
}