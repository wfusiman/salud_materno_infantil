package com.example.appsaludmi

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.appsaludmi.databinding.FragmentBasicInfoBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasicInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasicInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentBasicInfoBinding? = null
    private val binding get() = _binding!!

    // private lateinit var perfilViewModel: PerfilViewModel
    //private  val dataViewModel: InfoViewModel by activityViewModels()
    //private  val dataViewModel: PerfilUsuarioViewModel by activityViewModels()
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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBasicInfoBinding.inflate( inflater,container,false)
        val view = binding.root

        // perfilViewModel = ViewModelProvider(requireActivity()).get(PerfilViewModel::class.java)
        // dataViewModel = ViewModelProvider(requireActivity()).get(PerfilUsuarioViewModel::class.java)

        binding.editTextNombre.setText( dataViewModel.nombre.value)
        binding.textViewNacimiento.setText( dataViewModel.fechaNacimiento.value )
        binding.textViewDomicilio.setText( dataViewModel.domicilio.value )
        binding.textViewConcepcion.setText( dataViewModel.fechaConcepcion.value )

        binding.textViewNacimiento.setOnClickListener { actionFechaNac() }
        binding.textViewDomicilio.setOnClickListener { actionSelectDomicilio() }
        binding.textViewConcepcion.setOnClickListener {  actionFechaConcepcion() }
        binding.btnGuardar.setOnClickListener {  actionGuardar() }
        return view
    }

    private fun actionGuardar() {
        val na = binding.editTextNombre.text.toString()
        val fn = binding.textViewNacimiento.text.toString()
        val dom = binding.textViewDomicilio.text.toString()
        val fc = binding.textViewConcepcion.text.toString()
        if (na.isEmpty() || fn.isEmpty() || dom.isEmpty() || fc.isEmpty()) {
            val toast = Toast.makeText( context, "Debe completar todos los campos", Toast.LENGTH_LONG )
            toast.setGravity(Gravity.BOTTOM,0,100);
            toast.show()
        }
        else {
            saveModel()
            updateBasicInfo()
        }
    }

    private fun actionSelectDomicilio() {
        saveModel()
        val dir = dataViewModel.domicilio.value.toString()
        val latlng= dataViewModel.latlng.value.toString()
        val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToMapDomicilioSelectFragment( )
        findNavController().navigate( action )
    }

    private fun actionFechaNac() {
        saveModel()
        val dialogFecha = DatePickerFragment { year, month, day ->
            binding.textViewNacimiento.setText( "$day / $month / $year")
        }
        dialogFecha.show( parentFragmentManager, "DatePicker")
    }

    private fun saveModel() {
        dataViewModel.setNombre( binding.editTextNombre.text.toString() )
        dataViewModel.setFechaNac( binding.textViewNacimiento.text.toString() )
        dataViewModel.setDomicilio(binding.textViewDomicilio.text.toString())
        dataViewModel.setFechaConcepcion( binding.textViewConcepcion.text.toString() )
    }

    private fun actionFechaConcepcion() {
        saveModel()
        val dialogFecha = DatePickerFragmentConcepcion { year, month, day ->
            binding.textViewConcepcion.setText( "$day / $month / $year" )
        }
        dialogFecha.show( parentFragmentManager, "DatePicker")
    }


    private fun updateBasicInfo() {
        val user = auth.currentUser
        if (user != null) {
            val perfilUpd: HashMap<String,Any> = HashMap<String,Any>()
            perfilUpd.put("nombre_apellido", dataViewModel.nombre.value.toString())
            perfilUpd.put("fechaNacimiento", dataViewModel.fechaNacimiento.value.toString())
            perfilUpd.put("domicilio", dataViewModel.domicilio.value.toString())
            perfilUpd.put("latlng", dataViewModel.latlng.value.toString())
            perfilUpd.put("fechaConcepcion", dataViewModel.fechaConcepcion.value.toString())

            val ref = fdb.getReference("perfiles")
            ref.child( user.uid ).updateChildren(perfilUpd )
                .addOnSuccessListener {
                    val action = BasicInfoFragmentDirections.actionBasicInfoFragmentToEditPerfilFragment()
                    findNavController().navigate( action )
                }
                .addOnFailureListener {
                    val toast = Toast.makeText( context, "No se pudo actualizar perfil", Toast.LENGTH_LONG )
                    toast.setGravity(Gravity.BOTTOM,0,100);
                    toast.show()
                }
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BasicInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BasicInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class DatePickerFragment(val listener: (Any?, Any?, Any?) -> Unit): DialogFragment(),DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            c.add( Calendar.YEAR, -20)
            val anio = c.get( Calendar.YEAR)
            val mes = c.get(Calendar.MONTH)
            val dia = c.get( Calendar.DAY_OF_MONTH)

            return DatePickerDialog( requireActivity(), this, anio, mes, dia )
        }
        override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
            listener(y,m+1,d)
        }

    }

    class DatePickerFragmentConcepcion(val listener: (Any?, Any?, Any?) -> Unit): DialogFragment(),DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            c.add( Calendar.DATE, -30 )
            val anio = c.get( Calendar.YEAR)
            val mes = c.get(Calendar.MONTH)
            val dia = c.get( Calendar.DAY_OF_MONTH)

            return DatePickerDialog( requireActivity(), this, anio, mes, dia )
        }
        override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
            listener(y,m+1,d)
        }

    }
}