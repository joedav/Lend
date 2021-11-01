package com.joelysondavid.lend.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.databinding.FragmentOwingBinding
import com.joelysondavid.lend.service.constants.DataBaseConstants
import com.joelysondavid.lend.service.constants.LendConstants
import com.joelysondavid.lend.view.adapter.DebtorAdapter
import com.joelysondavid.lend.view.listener.DebtorListener
import com.joelysondavid.lend.viewmodel.OwingViewModel

class OwingFragment : Fragment() {

    private lateinit var owingViewModel: OwingViewModel
    private var _binding: FragmentOwingBinding? = null

    private val mAdapter: DebtorAdapter = DebtorAdapter()
    private lateinit var mListener: DebtorListener

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

        mListener = object : DebtorListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, LendFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(LendConstants.DEBTORID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }
        }
        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        owingViewModel.load()
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