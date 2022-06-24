package com.example.appsaludmi

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appsaludmi.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //private lateinit var perfilViewModel: PerfilViewModel

    //private val dataViewModel: InfoViewModel  by activityViewModels()
    //private  val dataViewModel: PerfilUsuarioViewModel by activityViewModels()

    //private lateinit var dataViewModel: PerfilUsuarioViewModel


    private lateinit var mAuth: FirebaseAuth
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
        _binding = FragmentLoginBinding.inflate( inflater, container, false )
        val view = binding.root

        // perfilViewModel = ViewModelProvider( requireActivity()).get(PerfilViewModel::class.java)
        //dataViewModel = ViewModelProvider( requireActivity()).get(PerfilUsuarioViewModel::class.java)

        mAuth = FirebaseAuth.getInstance()

        binding.btnIngresar.setOnClickListener { sigInUsuario() }
        return view
    }

    private fun sigInUsuario() {
        val usr = binding.editTextUsr.text.toString()
        val pass = binding.editTextPasswdLogin.text.toString()

        if (usr.isEmpty() || pass.isEmpty()) {
            var toastr = Toast.makeText( context, "Ingrese email/password de usuario", Toast.LENGTH_LONG )
            toastr.setGravity( Gravity.TOP, 0, 100)
            toastr.show()
            return;
        }
        activity?.let {
            mAuth.signInWithEmailAndPassword( usr,pass )
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        //cargarDatosUsuario()
                        val principalAct = Intent( activity, MainActivity::class.java )
                        startActivity( principalAct )
                    } else {
                        var toastr = Toast.makeText( context, "Usuario o contraseña invalida", Toast.LENGTH_LONG )
                        toastr.setGravity( Gravity.TOP, 0,100)
                        toastr.show()
                    }
            }
        }

    }

    /*private fun cargarDatosUsuario() {
        val userID = mAuth.currentUser?.uid
        val ref: DatabaseReference = fdb.reference
        if (userID != null) {
            ref.child("perfiles").child(userID).get().addOnSuccessListener {
                dataViewModel.setNombre(it.child("nombre_apellido").value.toString())
                dataViewModel.setDomicilio( it.child("domicilio").value.toString())
                dataViewModel.setLatlng( it.child("latlng").value.toString())
                dataViewModel.setFechaNac( it.child("fechaNacimiento").value.toString())
                dataViewModel.setFechaConcepcion( it.child("fechaConcepcion").value.toString() )
                dataViewModel.setFuma(it.child("fumadora").value as Boolean)
                dataViewModel.setFumaConvive(it.child("conviveFumadores").value as Boolean)
                dataViewModel.setPersonas( it.child("personasHogar").value as Long )
                dataViewModel.setHabitaciones( it.child("habitaciones").value as Long )
                dataViewModel.setCalefaccion( it.child("calefaccion").value.toString() )

                Log.i("SMI Login","nombre: " + dataViewModel.nombre.value.toString() );
            }.addOnFailureListener {
                Log.e("Firebase","Error getting data ")
            }
        }
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}