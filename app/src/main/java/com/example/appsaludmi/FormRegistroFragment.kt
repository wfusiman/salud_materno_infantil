package com.example.appsaludmi

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.appsaludmi.databinding.FragmentFormRegistroBinding
import com.example.appsaludmi.db.model.Perfil
import com.example.appsaludmi.viewModels.PerfilViewModel

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

    private var _binding: FragmentFormRegistroBinding? = null
    private val binding get() = _binding!!

    //private val regViewModel: RegistroViewModel by viewModels()
    private lateinit var perfilViewModel: PerfilViewModel

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
        // _binding = DataBindingUtil.inflate( inflater, R.layout.fragment_form_registro, container, false )
        _binding = FragmentFormRegistroBinding.inflate( inflater, container, false )
        val view = binding.root

        perfilViewModel =ViewModelProvider(requireActivity()).get(PerfilViewModel::class.java)

        binding.brnRegistrar.setOnClickListener {
            if (binding.editTextUsr.length() > 0 && binding.editTextPasswd.length() > 0 && binding.editTextPasswd2.length() > 0) {
                val usr = binding.editTextUsr.text.toString()
                val pass = binding.editTextPasswd.text.toString()
                val pass2 = binding.editTextPasswd2.text.toString()
                if (pass != pass2) {
                    val toast = Toast.makeText( context, "Las contraseñas no coinciden", Toast.LENGTH_LONG )
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show()
                }
                else {
                    val stat = perfilViewModel.isUsuarioRegistrado( usr )
                    if (stat) {
                        val toast = Toast.makeText( context, "El usuario ya esta registrado", Toast.LENGTH_LONG )
                        toast.setGravity(Gravity.BOTTOM,0,0);
                        toast.show()
                    }
                    else {
                        val perfil: Perfil = Perfil(
                                usr = usr,
                                passwd = pass,
                                nombre = "",
                                apellido = "",
                                domicilio = "",
                                fechaNacimiento = "" )
                        perfilViewModel.registrar( perfil )

                        val action = FormRegistroFragmentDirections.actionFormRegistroFragmentToEditPerfilFragment( usr )
                        view.findNavController().navigate( action )
                    }
                }

            }

        }

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