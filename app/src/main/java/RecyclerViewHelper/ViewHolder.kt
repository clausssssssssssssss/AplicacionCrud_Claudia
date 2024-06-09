package RecyclerViewHelper

import android.view.View
import android.media.Image
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.viewmodel.savedstate.R
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var textView: TextView = view.findViewById(R.id.txtTicketCard)
    val imgEditarT: ImageView = view.findViewById(R.id.imgEditar)
    val imgBorrarT: ImageView = view.findViewById(R.id.imgBorrar)
}