package RecyclerViewHelper

import Modelo.DataClassTickest
import androidx.recyclerview.widget.RecyclerView

class Adaptador (private var Datos: List<DataClassTickest>): RecyclerView.Adapter<ViewHolder>() {

    fun ActualizarLista(nuevaLista: List<DataClassTickest>){
        Datos = nuevaLista
        notifyDataSetChanged()
    }
    fun AactualizarListaDespuesDeActualizarDatos(UUID: String ,ticket: Int ,titulo: String ,descripcion: String ,Autor: String ,Email: String ,Creacion: String , Estado: String ,Finalizacion: String){
        val index = Datos.indexOfFirst{ it.UUID == UUID}
        Datos[index].Ticket = nuevoTicket
        notify
    }
}