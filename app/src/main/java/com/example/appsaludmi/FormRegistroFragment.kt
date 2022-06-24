package com.example.appsaludmi

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.appsaludmi.databinding.FragmentFormRegistroBinding
import com.example.appsaludmi.db.model.PerfilUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
    // private lateinit var perfilViewModel: PerfilViewModel
    private lateinit var auth: FirebaseAuth
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
        // _binding = DataBindingUtil.inflate( inflater, R.layout.fragment_form_registro, container, false )
        _binding = FragmentFormRegistroBinding.inflate( inflater, container, false )
        val view = binding.root

        // perfilViewModel =ViewModelProvider(requireActivity()).get(PerfilViewModel::class.java)

        auth = FirebaseAuth.getInstance()
        binding.btnRegistrar.setOnClickListener { registrarUsuario() }

        return view
    }

    private fun registrarUsuario() {
        val usr = binding.editTextUsr.text.toString()
        val pass = binding.editTextPasswd.text.toString()
        val pass2 = binding.editTextPasswd2.text.toString()

        if (usr.isEmpty()) {
            val toastr = Toast.makeText( context, "Debe ingresar usuario", Toast.LENGTH_LONG )
            toastr.setGravity(Gravity.TOP, 0,100 )
            toastr.show()
            return
        }
        if (pass.isEmpty()) {
            val toastr = Toast.makeText( context, "Debe ingresar password", Toast.LENGTH_LONG )
            toastr.setGravity(Gravity.TOP, 0,100 )
            toastr.show()
            return
        }
        if (pass2.isEmpty()) {
            val toastr = Toast.makeText( context, "Debe re ingresar password", Toast.LENGTH_LONG )
            toastr.setGravity(Gravity.TOP, 0,100 )
            toastr.show()
            return
        }
        if (pass != pass2) {
            val toastr = Toast.makeText( context, "Las contraseñas no coinciden", Toast.LENGTH_LONG )
            toastr.setGravity(Gravity.TOP, 0,100 )
            toastr.show()
            return
        }
        activity?.let {
                auth.createUserWithEmailAndPassword(usr,pass)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            val userID = auth.currentUser?.uid
                            val ref: DatabaseReference = fdb.reference
                            val usr = PerfilUsuario( usuario = usr, nombre_apellido = "", domicilio = "", latlng="", fechaNacimiento = "", fechaConcepcion = "", personasHogar = 0, habitaciones = 0, calefaccion = "", fumadora = false, conviveFumadores = false )
                            if (userID != null) {
                                ref.child("perfiles").child(userID).setValue( usr )
                            };

                            Log.i("Registro", "CreateUserWithEmail sucesfull")
                            val toastr  = Toast.makeText( context, "Registro exitoso, inicie sesion", Toast.LENGTH_LONG)
                            toastr.setGravity(Gravity.TOP, 0,100 )
                            toastr.show()
                            val loginFragment = FormRegistroFragmentDirections.actionFormRegistroFragmentToLoginFragment()
                            findNavController().navigate( loginFragment )
                        }
                        else {
                            var msj = task.exception?.message
                            Log.i("Registro","CreateUserWithEmail fallo " + msj )

                            if (msj!= null && msj.contains("The email address is already in use")) {
                                val toastr  = Toast.makeText( context, "El email ya se encuentra registrado", Toast.LENGTH_LONG)
                                toastr.setGravity(Gravity.TOP, 0,100 )
                                toastr.show()
                            }
                            else {
                                val toastr = Toast.makeText( context, msj, Toast.LENGTH_LONG)
                                toastr.setGravity(Gravity.TOP, 0,100 )
                                toastr.show()
                            }
                        }
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