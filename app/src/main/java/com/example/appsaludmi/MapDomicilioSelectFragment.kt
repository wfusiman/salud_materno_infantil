package com.example.appsaludmi

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appsaludmi.databinding.FragmentMapDomicilioSelectBinding
import com.example.appsaludmi.viewModels.InfoViewModel
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import com.example.appsaludmi.viewModels.PerfilViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapDomicilioSelectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapDomicilioSelectFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMapDomicilioSelectBinding? = null
    private val binding get() = _binding!!

    /*private lateinit var perfilViewModel: PerfilViewModel
    private val dataViewModel: InfoViewModel by activityViewModels()*/
    private lateinit var dataViewModel: PerfilUsuarioViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //val args: MapDomicilioSelectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapDomicilioSelectBinding.inflate( inflater,container,false)
        val view = binding.root

        //perfilViewModel = ViewModelProvider(requireActivity()).get(PerfilViewModel::class.java)
        dataViewModel = ViewModelProvider(requireActivity()).get(PerfilUsuarioViewModel::class.java)

        binding.editTextDireccionMap.setText( dataViewModel.domicilio.value )


        binding.btnAceptarMap.setOnClickListener { actionGuardar() }
        return view
    }

    private fun actionGuardar() {
        if (binding.editTextDireccionMap.text.isEmpty()) {
            val toast = Toast.makeText( context, "Ingrese direccion", Toast.LENGTH_LONG )
            toast.setGravity(Gravity.BOTTOM,0,100);
            toast.show()
        }
        else {
            dataViewModel.setDomicilio( binding.editTextDireccionMap.text.toString() )
            val action = MapDomicilioSelectFragmentDirections.actionMapDomicilioSelectFragmentToBasicInfoFragment( )
            findNavController().navigate( action )

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapDomicilioSelectFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapDomicilioSelectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}