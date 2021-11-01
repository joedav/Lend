package com.joelysondavid.lend.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.databinding.FragmentOwingBinding
import com.joelysondavid.lend.view.adapter.DebtorAdapter
import com.joelysondavid.lend.viewmodel.OwingViewModel
import kotlinx.android.synthetic.main.fragment_owing.*
import kotlinx.android.synthetic.main.fragment_owing.view.*

class OwingFragment : Fragment() {

    private lateinit var owingViewModel: OwingViewModel
    private var _binding: FragmentOwingBinding? = null
    private val mAdapter: DebtorAdapter = DebtorAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        owingViewModel =
            ViewModelProvider(this).get(OwingViewModel::class.java)

        _binding = FragmentOwingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recycler View
        // 1 - Obter a recycler
        val recycler: RecyclerView = root.findViewById(R.id.recycler_owing)

        // 2 - Definir um Layout
        recycler.layoutManager = LinearLayoutManager(root.context)

        // 3 - Definir um adapter
        recycler.adapter = mAdapter

        observer()

        owingViewModel.load()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        owingViewModel.debtorsList.observe(
            viewLifecycleOwner,
            Observer {
                mAdapter.updateDebtors(it)
            })
    }
}