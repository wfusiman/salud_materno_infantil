package com.example.appsaludmi.noticias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsaludmi.R
import com.example.appsaludmi.databinding.FragmentNoticiaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoticiaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoticiaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentNoticiaBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentNoticiaBinding.inflate( inflater, container, false )
        val view = binding.root
        binding.noticia0RadioButton.setOnClickListener { clickNoticia( R.id.noticia0RadioButton) }
        binding.noticia1RadioButton.setOnClickListener { clickNoticia( R.id.noticia1RadioButton) }
        binding.noticia2RadioButton.setOnClickListener { clickNoticia( R.id.noticia2RadioButton) }
        return view
    }

    private fun clickNoticia(noticiaRadioButton: Int) {
        val index = when (noticiaRadioButton) {
            R.id.noticia0RadioButton -> 0
            R.id.noticia1RadioButton -> 1
            else -> 2
        }
        val act = getActivity()
        if (act is CoordinadoraNoticias)
            act.onChangeNoticia( index )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoticiaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoticiaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}