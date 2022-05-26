package com.example.appsaludmi

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.appsaludmi.databinding.FragmentBasicInfoBinding
import androidx.navigation.fragment.findNavController
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasicInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasicInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentBasicInfoBinding? = null
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
        _binding = FragmentBasicInfoBinding.inflate( inflater,container,false)
        val view = binding.root

        binding.editTextFNac.setOnClickListener { actionFechaNac() }
        binding.editTextDomicilio.setOnClickListener { actionSelectDomicilio() }
        return view
    }

    private fun actionSelectDomicilio() {
        findNavController().navigate(R.id.action_basicInfoFragment_to_mapDomicilioFragment )
    }

    private fun actionFechaNac() {
        val dialogFecha = DatePickerFragment { year, month, day ->  cargarResultado( year,month,day )}
        dialogFecha.show( parentFragmentManager, "DatePicker")
    }

    private fun cargarResultado(year: Int, month: Int, day: Int) {
        binding.editTextFNac.setText( "$day / $month / $year" )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BasicInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BasicInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class DatePickerFragment ( val listener : (year: Int, month: Int, day:Int ) -> Unit): DialogFragment(),DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val anio = c.get( Calendar.YEAR)
            val mes = c.get(Calendar.MONTH)
            val dia = c.get( Calendar.DAY_OF_MONTH)

            return DatePickerDialog( requireActivity(), this, anio, mes, dia )
        }
        override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
            listener(y,m+1,d)
        }

    }
}