package com.example.appsaludmi.recomendaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsaludmi.R
import com.example.appsaludmi.databinding.FragmentItemRecomendacionBinding
import com.example.appsaludmi.databinding.FragmentNoticiaBinding
import com.example.appsaludmi.recomendaciones.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ItemRecomendacionFragment : Fragment() {

    private var columnCount = 1
    private var _binding: FragmentItemRecomendacionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemRecomendacionBinding.inflate( inflater, container, false )
        val view = binding.root

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 ->  LinearLayoutManager( getContext() )
                    else -> GridLayoutManager( getContext(), columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemRecomendacionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}