package claudia.ortega.aplicacioncrudclaudia

import Modelo.ClassConexion
import Modelo.DataClassTickets
import RecyclerViewHelper.Adaptador
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class Tickets : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtTickets = findViewById<EditText>(R.id.txtTicketT)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloT)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcionT)
        val txtAutor = findViewById<EditText>(R.id.txtAutorT)
        val txtEmail = findViewById<EditText>(R.id.txtEmailT)
        val txtFechadeC = findViewById<EditText>(R.id.txtFechadeCT)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoT)
        val txtFechadeF = findViewById<EditText>(R.id.txtFechadeFT)
        val btnIngresarT = findViewById<Button>(R.id.btnIngresarT)

        fun Limpiar(){
            txtTickets.setText("")
            txtTitulo.setText("")
            txtDescripcion.setText("")
            txtAutor.setText("")
            txtEmail.setText("")
            txtFechadeC.setText("")
            txtEstado.setText("")
            txtFechadeF.setText("")
        }
        val rcvTickets = findViewById<RecyclerView>(R.id.rcvTickets)
        rcvTickets .layoutManager = LinearLayoutManager(this)
        fun obtenerDatos(): List<DataClassTickets>{
            val objConexion = ClassConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resulset = statement?.executeQuery("select * from TBticketsh")!!
            val Tickets = mutableListOf<DataClassTickets>()
            while (resulset.next()){
                val UUID = resulset.getString("UUID")
                val Ticketsss = resulset.getInt("ID_Ticket")
                val Titulo = resulset.getString("titulo")
                val Descripcion = resulset.getString("descripcion")
                val Autor = resulset.getString("Autor")
                val Email = resulset.getString("Email")
                val FechadeC =  resulset.getString("Creacion")
                val Estado = resulset.getString("Estado")
                val FechadeF = resulset.getString("Finalizacion")
                val Ticket = DataClassTickets(UUID,Ticketsss, Titulo, Descripcion, Autor, Email, FechadeC, Estado, FechadeF)
            Tickets.add(Ticket)
            }
            return Tickets
        }
        CoroutineScope(Dispatchers.IO).launch {
            val Tickets = obtenerDatos()
            withContext(Dispatchers.Main){
                val miAdaptar = Adaptador(Tickets)
                rcvTickets.adapter = miAdaptar
            }
        }
        btnIngresarT.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val classConexion = ClassConexion().cadenaConexion()
                val addTicket = classConexion?.prepareStatement("insert into TBticketsh (UUUID, ID_Ticket,  titulo , descripcion ,Autor, Email,  Creacion, Estado, Finalizacion) values(?,?,?,?,?,?,?,?,?)")!!
                addTicket.setString(1, UUID.randomUUID().toString())
                addTicket.setInt(2, txtTickets.text.toString().toInt())
                addTicket.setString(3, txtTitulo.text.toString())
                addTicket.setString(4, txtDescripcion.text.toString())
                addTicket.setString(5, txtAutor.text.toString())
                addTicket.setString(6, txtEmail.text.toString())
                addTicket.setString(7, txtFechadeC.text.toString())
                addTicket.setString(8, txtEstado.text.toString())
                addTicket.setString(9, txtFechadeF.text.toString())

                val NuevosTickets = obtenerDatos()
                withContext(Dispatchers.Main){
                    (rcvTickets.adapter as? Adaptador)?.ActualizarLista(NuevosTickets)
                }

            }
            Limpiar()
        }
    }
}