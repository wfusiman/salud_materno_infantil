package com.example.appsaludmi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.appsaludmi.databinding.FragmentViewRegistroBinding
import com.example.appsaludmi.viewModels.PerfilViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [viewRegistroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class viewRegistroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentViewRegistroBinding
    //private val viewModel: RegistroViewModel by viewModels()
    //val args: viewRegistroFragmentArgs by navArgs()
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewRegistroBinding.inflate( inflater, container, false )
        val view = binding.root

        perfilViewModel = ViewModelProvider(this)[PerfilViewModel::class.java]
        //val usr = args.usuario

        val perfil = perfilViewModel.getPerfil( "usr" )
        if (perfil != null) {
            binding.textViewUsrInput.text = perfil.usr
            binding.textViewApellidoInput.text = perfil.apellido
            binding.textViewNombreInput.text = perfil.nombre
            binding.textViewDomicilioInput.text = perfil.domicilio
            binding.textViewFechaNacInput.text = perfil.fechaNacimiento
        }
        else {
            binding.textViewTituloViewRegistro.text = "No se encontro perfil"
            //binding.textViewUsrInput.text = usr
            binding.textViewUsrInput.text = "usr"
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
         * @return A new instance of fragment viewRegistroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            viewRegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}