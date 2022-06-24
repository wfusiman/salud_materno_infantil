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
import com.example.appsaludmi.databinding.FragmentBasicInfoBinding
import com.example.appsaludmi.databinding.FragmentTabaquismoInfoBinding
import com.example.appsaludmi.viewModels.InfoViewModel
import com.example.appsaludmi.viewModels.PerfilUsuarioViewModel
import com.example.appsaludmi.viewModels.PerfilViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [TabaquismoInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabaquismoInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentTabaquismoInfoBinding? = null
    private val binding get() = _binding!!
    //private  val dataViewModel: InfoViewModel by activityViewModels()
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
        _binding = FragmentTabaquismoInfoBinding.inflate( inflater,container,false)
        val view = binding.root

        binding.checkBoxFuma.isChecked = dataViewModel.fuma.value as Boolean
        binding.checkBoxFunadores.isChecked = dataViewModel.fumaConvive.value as Boolean

        binding.btnGuardarTaquismo.setOnClickListener {  actionGuardar() }
        return view
    }

    private fun actionGuardar() {
        dataViewModel.setFuma( binding.checkBoxFuma.isChecked )
        dataViewModel.setFumaConvive( binding.checkBoxFunadores.isChecked )

        updateTabaquismoInfo()
    }

    private fun updateTabaquismoInfo() {
        val user = auth.currentUser
        if (user != null) {
            val tabaqUpd: HashMap<String, Any> = HashMap<String,Any>()
            tabaqUpd.put("fumadora", dataViewModel.fuma.value.toString().toBoolean())
            tabaqUpd.put("conviveFumadores", dataViewModel.fumaConvive.value.toString().toBoolean())

            val ref = fdb.getReference("perfiles")
            ref.child( user.uid ).updateChildren(tabaqUpd )
                .addOnSuccessListener {
                    val action = TabaquismoInfoFragmentDirections.actionTabaquismoInfoFragmentToEditPerfilFragment()
                    findNavController().navigate( action )
                }
                .addOnFailureListener {
                    val toast = Toast.makeText( context, "No se pudo actualizar informacion de tabaquismo", Toast.LENGTH_LONG )
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
         * @return A new instance of fragment TabaquismoInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TabaquismoInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}