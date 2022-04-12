package com.example.appsaludmi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.appsaludmi.databinding.FragmentFormRegistroBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

const val EXTRA_MESSAGE_1 = "MESSAGE_DATA1"
const val EXTRA_MESSAGE_2 = "MESSAGE_DATA2"
const val EXTRA_MESSAGE_3 = "MESSAGE_DATA3"
const val EXTRA_MESSAGE_4 = "MESSAGE_DATA4"

/**
 * A simple [Fragment] subclass.
 * Use the [FormRegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormRegistroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFormRegistroBinding
    private lateinit var model: RegistroViewModel

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
        binding = FragmentFormRegistroBinding.inflate( inflater, container, false )
        val view = binding.root

        model = ViewModelProvider( this ).get( RegistroViewModel::class.java );

        binding.editTextApellido.setText( model.apellido )
        binding.editTextNombre.setText( model.nombre )
        binding.editTextDomicilio.setText( model.domicilio )
        binding.editTextFNacimiento.setText( model.fnac )

        binding.brnRegistrar.setOnClickListener { actionRegistrar() }
        return view
    }

    private fun actionRegistrar() {
        model.apellido = binding.editTextApellido.text.toString()
        model.nombre = binding.editTextNombre.text.toString()
        model.domicilio = binding.editTextDomicilio.text.toString()
        model.fnac = binding.editTextFNacimiento.text.toString()

        val intent = Intent( context, ViewRegistroActivity::class.java).apply {
            putExtra( EXTRA_MESSAGE_1, model.nombre )
            putExtra( EXTRA_MESSAGE_2, model.apellido)
            putExtra( EXTRA_MESSAGE_3, model.domicilio )
            putExtra( EXTRA_MESSAGE_4, model.fnac )
        }
        startActivity( intent )
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