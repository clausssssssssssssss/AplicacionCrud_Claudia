package claudia.ortega.aplicacioncrudclaudia

import Modelo.ClassConexion
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID


class activity_registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgAtrasRegistrar = findViewById<ImageView>(R.id.imgAtrasRegistrarse)
        val txtRegistarCorreo = findViewById<EditText>(R.id.txtCorreoReg)
        val txtContrasenaRegistro = findViewById<EditText>(R.id.txtContraseñaReg)
        val txtConfirmarContraReg = findViewById<EditText>(R.id.txtConfirmarContrReg)
        val imgVerContrar = findViewById<ImageView>(R.id.imgVerContra)
        val imgVerConfirContra = findViewById<ImageView>(R.id.imgVerConfirContra)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnCrearCuenta.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClassConexion().cadenaConexion()
                val crearUsuario =
                objConexion?.prepareStatement("INSERT INTO TBusuariosh(UUID_Usuario, email, Contrasena) VALUES (?, ?, ?)")!!
                crearUsuario.setString(1, UUID.randomUUID().toString())
                crearUsuario.setString(2,txtRegistarCorreo.text.toString())
                crearUsuario.setString(3, txtContrasenaRegistro.text.toString())
                crearUsuario.executeUpdate()
                withContext(Dispatchers.Main){
                    Toast.makeText(this@activity_registrar, "Tu usuario se creo, bienvenid@", Toast.LENGTH_SHORT).show()
                    txtRegistarCorreo.setText("")
                    txtContrasenaRegistro.setText("")
                    txtConfirmarContraReg.setText("")

                }
            }
        }
        imgVerContrar.setOnClickListener {
            if (txtContrasenaRegistro.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD){
                txtContrasenaRegistro.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else{
                txtContrasenaRegistro.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        imgVerConfirContra.setOnClickListener {
            if (txtConfirmarContraReg.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD){
                txtConfirmarContraReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }else{
                txtConfirmarContraReg.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }
        imgAtrasRegistrar.setOnClickListener {
            val pantallaLogin = Intent(this, activity_Login::class.java)
            startActivity(pantallaLogin)
        }
        btnIniciarSesion.setOnClickListener {
            val pantallaLogin = Intent(this, activity_Login::class.java)
            startActivity(pantallaLogin)
        }
    }
}

