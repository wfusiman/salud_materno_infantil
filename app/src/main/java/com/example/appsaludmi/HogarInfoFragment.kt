package com.example.appsaludmi

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.appsaludmi.databinding.FragmentHogarInfoBinding
import com.example.appsaludmi.databinding.FragmentTabaquismoInfoBinding
import com.example.appsaludmi.viewModels.InfoViewModel
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_hogar_info.*
import java.util.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HogarInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HogarInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHogarInfoBinding? = null
    private val binding get() = _binding!!
    // private  val dataViewModel: InfoViewModel by activityViewModels()
    private val dataViewModel: PerfilUsuarioViewModel by activityViewModels()

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val fdb: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHogarInfoBinding.inflate( inflater,container,false)
        val view = binding.root

        val spinn_cal: Spinner = binding.spinnerCalefaccion
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tipo_calefaccion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinn_cal.adapter = adapter
            val cal = dataViewModel.calefaccion
            val position = adapter.getPosition( cal.value.toString() )
            spinn_cal.setSelection( position )
        }

        val spinn_personas: Spinner = binding.spinnerPersonas
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cant_personas,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinn_personas.adapter = adapter

            val cant = dataViewModel.personas.value
            val position = adapter.getPosition( cant.toString() )
            spinn_personas.setSelection( position )
        }

        val spinn_hab: Spinner = binding.spinnerHabitaciones
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cant_habitaciones,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinn_hab.adapter = adapter
            val cant = dataViewModel.habitaciones.value
            val position = adapter.getPosition( cant.toString() )
            spinn_hab.setSelection( position )
        }
        binding.btnGuardarHogar.setOnClickListener {  actionGuardar() }
        return view
    }

    private fun actionGuardar() {
        saveModel()
        updateHogarInfo()
    }

    private fun updateHogarInfo() {
        val user = auth.currentUser
        if (user != null) {
            val hogarUpd: HashMap<String, Any> = HashMap<String,Any>()
            hogarUpd.put("personasHogar", dataViewModel.personas.value.toString().toLong())
            hogarUpd.put("habitaciones", dataViewModel.habitaciones.value.toString().toLong())
            hogarUpd.put("calefaccion", dataViewModel.calefaccion.value.toString())

            val ref = fdb.getReference("perfiles")
            ref.child( user.uid ).updateChildren(hogarUpd )
                .addOnSuccessListener {
                    val action = HogarInfoFragmentDirections.actionHogarInfoFragmentToEditPerfilFragment()
                    findNavController().navigate( action )
                }
                .addOnFailureListener {
                    val toast = Toast.makeText( context, "No se pudo actualizar informacion del hogar", Toast.LENGTH_LONG )
                    toast.setGravity(Gravity.BOTTOM,0,100);
                    toast.show()
                }
        }
    }

    private fun saveModel() {
        dataViewModel.setPersonas( spinner_personas.selectedItem.toString().toLong() )
        dataViewModel.setHabitaciones( spinner_habitaciones.selectedItem.toString().toLong() )
        dataViewModel.setCalefaccion( spinner_calefaccion.selectedItem.toString() )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HogarInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HogarInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}