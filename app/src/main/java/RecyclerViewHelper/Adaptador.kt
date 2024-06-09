package RecyclerViewHelper

import Modelo.ClassConexion
import Modelo.DataClassTickets
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.aplicacioncrudclaudia.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Adaptador(private var Datos: List<DataClassTickets>): RecyclerView.Adapter<ViewHolder>() {

    fun ActualizarLista(nuevaLista: List<DataClassTickets>){
        Datos = nuevaLista
        notifyDataSetChanged()
    }
    fun actualizarListaDespuesDeActualizarDatos(UUID: String ,nuevoTicket: Int){

        val index = Datos.indexOfFirst{ it.UUID == UUID}
        Datos[index].ID_ticket = nuevoTicket
        notifyItemChanged(index)
    }
    fun eliminarRegistro(ID_ticket: Int, Position: Int){

    val ListaDatos = Datos.toMutableList()
    ListaDatos.removeAt(Position)

    GlobalScope.launch(Dispatchers.IO){
        val objConexion = ClassConexion().cadenaConexion()
        val delTicket = objConexion?.prepareStatement("delete TBticketsh where ID_Ticket ")!!
        delTicket.setString(1, ID_ticket.toString())
        delTicket.executeUpdate()

        val commit = objConexion.prepareStatement("commit")!!
        commit.executeUpdate()
    }
        Datos = ListaDatos.toList()
        notifyItemChanged(Position)
        notifyDataSetChanged()
    }
    fun actualizarListadeTickets(ID_ticket: Int, UUID: String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClassConexion().cadenaConexion()
            val updateTicket = objConexion?.prepareStatement("update TBticketsh  set ID_Ticket = ? where UUID =?")!!
            updateTicket.setInt(1, ID_ticket)
            updateTicket.setString(2, UUID)
            updateTicket.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                actualizarListaDespuesDeActualizarDatos(UUID, ID_ticket)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, Position: Int) {
        val Tickets = Datos[Position]
        holder.textView.text = Tickets.ID_ticket.toString()

        val item = Datos[Position]
        holder.imgBorrarT.setOnClickListener {
            holder.imgBorrarT.setOnClickListener {
              val context = holder.itemView.context
              val builder = AlertDialog.Builder(context)
                builder.setTitle("Quieres borrarlo")
                builder.setPositiveButton("Si"){dialog, wich ->
                    eliminarRegistro(item.ID_ticket,Position)
            }
                builder.setNegativeButton(  "No"){ dialog, wich ->
            }
                val alertDialog = builder.create()
                alertDialog.show()
      }
            holder.imgActualizarT.setOnClickListener {
                val context = holder.itemView.context
                val builder= AlertDialog.Builder(context)
                builder.setTitle("Editar nombre")
                val cuadritoNuevoNombre = EditText(context)
                cuadritoNuevoNombre.setHint(item.ID_ticket)
                builder.setView(cuadritoNuevoNombre)

                builder.setPositiveButton("Actualisar Ticket"){dialog, wich ->
                    actualizarListadeTickets(cuadritoNuevoNombre.text.toString(), item.UUID)
            }
                builder.setNegativeButton("Cancelar"){dialog, wich ->
                    dialog.dismiss()
    }
                val dialog = builder.create()
                dialog.show()
            }

        }
    }

    private fun actualizarListadeTickets(ID_ticket: String, UUID: String) {

    }
}
