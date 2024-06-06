package claudia.ortega.aplicacioncrudclaudia

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

            val Usuario = findViewById<EditText>(R.id.txtUsuario)
            val Contraseña = findViewById<EditText>(R.id.txtContraseña)
            val Ingresar = findViewById<Button>(R.id.btnIngresar)

        Ingresar.setOnClickListener {
            val Usuario = Usuario.text.toString()
            val Contraseña = Contraseña.text.toString()

            if(Usuario == "Bryan" && Contraseña == "ITR2024" ) {
                Toast.makeText(this, "Adelante", Toast.LENGTH_SHORT).show()
           } else {
               Toast.makeText(this, "Usuario o Contraseña Invalido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
