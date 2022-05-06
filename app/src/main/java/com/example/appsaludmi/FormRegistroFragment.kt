package com.example.appsaludmi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appsaludmi.databinding.FragmentFormRegistroBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormRegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormRegistroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var _binding: FragmentFormRegistroBinding
    private val regViewModel: RegistroViewModel by viewModels()

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
        _binding = DataBindingUtil.inflate( inflater, R.layout.fragment_form_registro, container, false )
        //_binding = FragmentFormRegistroBinding.inflate( inflater, container, false )
        val view = _binding.root

        _binding.registroViewModel = regViewModel
        _binding.lifecycleOwner = viewLifecycleOwner

        _binding.editTextApellido.setText( regViewModel.apellido.value )
        _binding.editTextNombre.setText( regViewModel.nombre.value )
        _binding.editTextDomicilio.setText( regViewModel.domicilio.value )
        _binding.editTextFNacimiento.setText( regViewModel.fnac.value )

        _binding.editTextNombre.addTextChangedListener{
            Log.i("Change value nombre","Value nombre: " + _binding.editTextNombre.text.toString() )
            regViewModel.registryNombre( _binding.editTextNombre.text.toString())
        }

        _binding.brnRegistrar.setOnClickListener{
            regViewModel.registry( _binding.editTextNombre.text.toString(),
                _binding.editTextApellido.text.toString() ,
                _binding.editTextDomicilio.text.toString(),
                _binding.editTextFNacimiento.text.toString() )
            /*val action = FormRegistroFragmentDirections.actionFormRegistroFragmentToViewRegistroFragment(
                model.apellido.value.toString(),
                model.nombre.value.toString(),
                model.domicilio.value,
                model.fnac.value )*/
            val action = FormRegistroFragmentDirections.actionFormRegistroFragmentToViewRegistroFragment()
            view.findNavController().navigate( action )
        }

        regViewModel.nombre.observe( viewLifecycleOwner , Observer { n -> Log.i("info","nombre observer: " + n ) })
        /*regViewModel.apellido.observe( viewLifecycleOwner, Observer { a -> regViewModel.registryApellido(a) })
        regViewModel.domicilio.observe( viewLifecycleOwner, Observer { d -> regViewModel.registruDomicilio(d) })
        regViewModel.fnac.observe( viewLifecycleOwner, Observer { fn -> regViewModel.registryFechaNacimiento( fn ) })*/
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormRegistroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormRegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}