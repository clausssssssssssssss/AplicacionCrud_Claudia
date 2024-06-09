package RecyclerViewHelper

import android.view.View
import android.media.Image
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import claudia.ortega.aplicacioncrudclaudia.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var textView: TextView = view.findViewById(R.id.txtTicketCard)
    val imgActualizarT: ImageView = view.findViewById(R.id.imgActualizarT)
    val imgBorrarT: ImageView = view.findViewById(R.id.imgBorrarT)
}