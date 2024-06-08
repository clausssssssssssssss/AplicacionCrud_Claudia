package RecyclerViewHelper

import Modelo.ClassConexion
import Modelo.DataClassTickest
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Adaptador(private var Datos: List<DataClassTickest>): RecyclerView.Adapter<ViewHolder>() {

    fun ActualizarListaDeTickets(nuevaLista: List<DataClassTickest>){
        Datos = nuevaLista
        notifyDataSetChanged()
    }
    fun ActualizarListaDeTicketsDespues(UUID: String ,ID_ticket: Int ,titulo: String ,descripcion: String ,Autor: String ,Email: String ,Creacion: String , Estado: String ,Finalizacion: String){
        val index = Datos.indexOfFirst{ it.UUID == UUID}
        Datos[index].ID_ticket = ID_ticket
        notifyItemChanged(index)
    }
    fun eliminarRegistro(ID_ticket: Int, Position: Int){
    val ListaDatos = Datos.toMutableList()
    ListaDatos.removeAt(Position)

    GlobalScope.launch(Dispatchers.IO){
        val objConexion = ClassConexion().cadenaConexion()
        val delTicket = objConexion?.prepareStatement("delete TBticketsh where ID_Ticket ")!!
        delTicket.setString(1,ID_ticket)
        delTicket.executeUpdate()

        val commit = objConexion.prepareStatement("commit")!!
        commit.executeUpdate()
    }
        Datos = ListaDatos.toList()
        notifyItemChanged(Position)
        notifyDataSetChanged()
    }
    fun actualizarProductos(ID_ticket: Int, UUID: String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClassConexion().cadenaConexion()
            val updateTicket = objConexion?.prepareStatement("update TBticketsh  set ID_Ticket = ? where UUID =?")!!
            updateTicket.setInt(1, ID_ticket)
            updateTicket.setString(2, UUID)
            updateTicket.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                ac
            }
        }

    }
}