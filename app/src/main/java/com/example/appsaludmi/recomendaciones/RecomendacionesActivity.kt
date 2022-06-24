package com.example.appsaludmi.recomendaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsaludmi.R
import com.example.appsaludmi.adapters.RecomendacionAdapter
import com.example.appsaludmi.databinding.ActivityRecomendacionesBinding
import com.example.appsaludmi.db.model.Recomendacion

class RecomendacionesActivity : AppCompatActivity() {

    private var _binding: ActivityRecomendacionesBinding? = null
    private val binding get() = _binding!!
    private var recomendaciones = ArrayList<Recomendacion>()
    private lateinit var adapter: RecomendacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecomendacionesBinding.inflate( layoutInflater )
        setContentView( binding.root )

        getRecomendaciones()

        val recyclerView = binding.list
        adapter = RecomendacionAdapter( this )
        adapter.setRecomendaciones( recomendaciones )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager( this )

        val spinn_filtro: Spinner = binding.spinnFiltro
        ArrayAdapter.createFromResource(
            this,
            R.array.actividades,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinn_filtro.adapter = adapter
            val position = adapter.getPosition( "todas" )
            spinn_filtro.setSelection( position )
        }

        spinn_filtro.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) { // todas
                    Log.i("item select","todas")
                    adapter.filter( "todas")
                }
                if (position == 1 ) { // alimentacion
                    Log.i("item select","alimentacion")
                    adapter.filter( "alimentacion")
                }
                if (position == 2 ) { // deportes
                    Log.i("item select","deporte")
                    adapter.filter( "deporte")
                }
                if (position == 3 ) { // salud
                    Log.i("item select","salud")
                    adapter.filter( "salud")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }
    }

    fun  getRecomendaciones() {
        val rec1 = Recomendacion()
        rec1.titulo = "Hábitos Saludables"
        rec1.contenido =  "Compre productos que tengan el sello de aprobación de la ADA. Siga una dieta equilibrada. Si come entre horas, hágalo con moderación. Visite al dentista frecuentemente para limpiezas y revisiones profesionales."
        rec1.categoria = "salud"
        rec1.imgResource = R.drawable.img1

        val rec2 = Recomendacion()
        rec2.titulo ="Alimentación saludable"
        rec2.contenido = "El consumo de alimentos saludables y bebidas bajas en calorías, en particular agua, y la cantidad adecuada de calorías puede ayudarles a usted y a su bebé a aumentar la cantidad adecuada de peso."
        rec2.categoria = "alimentacion"
        rec2.imgResource = R.drawable.img2

        val rec3 = Recomendacion()
        rec3.titulo ="Actividad fisica"
        rec3.contenido = "La actividad física está plenamente aconsejada durante el embarazo, según las recomendaciones del colegio americano de obstetras y ginecólogos, institución especializada en el tema. El feto no se ve perjudicado de ningún modo y significa un beneficio para la madre, porque además de mantener el tono muscular y manejar la parte calórica, la actividad muscular evita el hiperinsulinismo."
        rec3.categoria = "deporte"
        rec3.imgResource = R.drawable.img3

        recomendaciones.add( rec1 )
        recomendaciones.add( rec2 )
        recomendaciones.add( rec3 )
    }

}