package com.example.appsaludmi

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.appsaludmi.databinding.FragmentFormRegistroBinding
import com.example.appsaludmi.databinding.FragmentLoginBinding
import com.example.appsaludmi.viewModels.PerfilViewModel
import com.google.android.material.snackbar.Snackbar

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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate( inflater, container, false )
        val view = binding.root

        perfilViewModel = ViewModelProvider( requireActivity()).get(PerfilViewModel::class.java)

        binding.btnIngresar.setOnClickListener {
            if (perfilViewModel.validateUsuario( binding.editTextUsr.text.toString(), binding.editTextPasswdLogin.text.toString())) {
                Log.i("Login","Usuario validado")
                // Mostrar pantalla principal
            }
            else {
                Log.i("Login","Usuario no encontrado")
                val toast = Toast.makeText( context, "Usuario no registrado o contraseña invalida", Toast.LENGTH_LONG )
                toast.setGravity(Gravity.BOTTOM,0,0);

                toast.show()
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