package com.example.appsaludmi.adapters

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsaludmi.R
import com.example.appsaludmi.db.model.Recomendacion


class RecomendacionAdapter  internal  constructor( context: Context)
    : RecyclerView.Adapter<RecomendacionAdapter.RecomendacionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var recomendaciones: MutableList<Recomendacion> //

    private lateinit var recomendacionesTodas: List<Recomendacion> //

    inner class RecomendacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recomendacionItemTitulo: TextView = itemView.findViewById( R.id.RecTitulo )
        val recomendacionItemResumen: TextView = itemView.findViewById(R.id.RecDescripcion)
        val recomendacionItemImg: ImageView = itemView.findViewById(R.id.RecImg)
        var recomendacionItemCateg: TextView = itemView.findViewById(R.id.RecCategoria)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecomendacionViewHolder {
        Log.i("onCreateViewHolder"," ++++++  onCreateViewHolder ")
        val itemView = inflater.inflate( R.layout.fragment_item_recomendacion, parent, false)
        return RecomendacionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecomendacionViewHolder, position: Int) {
        val recomendacion = recomendaciones[position]
        Log.i("onBindViewHolder"," ++++++ recomendacion: " + recomendacion )
        holder.recomendacionItemTitulo.setText( "${recomendacion.titulo}")
        holder.recomendacionItemResumen.setText("${recomendacion.contenido}")
        holder.recomendacionItemImg.setImageResource( recomendacion.imgResource)
        holder.recomendacionItemCateg.setText( recomendacion.categoria )
    }

    internal fun setRecomendaciones(recs: List<Recomendacion>) {
        Log.i("set Recomendaciones"," ++++++ recomendaciones: " + recs.size )
        recomendacionesTodas = recs
        recomendaciones = recomendacionesTodas.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = recomendaciones.size

    fun filter( categoria: String) {
        if (categoria == "todas") {
            recomendaciones.clear()
            recomendaciones.addAll( recomendacionesTodas )
        } else {
                recomendaciones.clear()
                for (i in recomendacionesTodas) {
                    if (i.categoria.contains(categoria)) {
                        recomendaciones.add(i)
                    }
                }
        }
        notifyDataSetChanged()
    }
}