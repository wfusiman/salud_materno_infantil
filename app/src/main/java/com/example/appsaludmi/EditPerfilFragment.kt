package com.example.appsaludmi

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appsaludmi.databinding.FragmentEditPerfilBinding
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditPerfilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditPerfilFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentEditPerfilBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataViewModel: PerfilUsuarioViewModel

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
        _binding = FragmentEditPerfilBinding.inflate( inflater, container, false )
        val view = binding.root

        dataViewModel = ViewModelProvider(requireActivity()).get(PerfilUsuarioViewModel::class.java)
        Log.i("SMI Basic info","nombre: " + dataViewModel.nombre.value.toString() );

        binding.cardview1.setOnClickListener { actionEditBasicInfo() }
        binding.cardview2.setOnClickListener { actionEdtiHogarInfo() }
        binding.cardview3.setOnClickListener { actionEditTabaquismoInfo() }
        return view
    }

    private fun actionEdtiHogarInfo() {
        val action = EditPerfilFragmentDirections.actionEditPerfilFragmentToHogarInfoFragment()
        findNavController().navigate( action )
    }

    private fun actionEditTabaquismoInfo() {
        val action = EditPerfilFragmentDirections.actionEditPerfilFragmentToTabaquismoInfoFragment()
        findNavController().navigate( action )
    }

    private fun actionEditBasicInfo() {
        val action = EditPerfilFragmentDirections.actionEditPerfilFragmentToBasicInfoFragment()
        findNavController().navigate( action )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditPerfilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditPerfilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}