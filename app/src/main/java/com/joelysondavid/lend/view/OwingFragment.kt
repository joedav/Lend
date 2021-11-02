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
import com.joelysondavid.lend.service.constants.LendConstants
import com.joelysondavid.lend.view.adapter.DebtorAdapter
import com.joelysondavid.lend.view.listener.DebtorListener
import com.joelysondavid.lend.viewmodel.LendViewModel

class OwingFragment : Fragment() {

    private lateinit var mLendViewModel: LendViewModel
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
        mLendViewModel =
            ViewModelProvider(this).get(LendViewModel::class.java)

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

            override fun onDelete(id: Int) {
                mLendViewModel.delete(id)
                mLendViewModel.load(LendConstants.FILTER.DEBTORS)
            }
        }
        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mLendViewModel.load(LendConstants.FILTER.DEBTORS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        mLendViewModel.debtorsList.observe(
            viewLifecycleOwner,
            Observer {
                mAdapter.updateDebtors(it)
            })
    }
}