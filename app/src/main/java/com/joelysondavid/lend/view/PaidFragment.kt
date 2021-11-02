package com.joelysondavid.lend.view

import android.content.Intent
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
import com.joelysondavid.lend.databinding.FragmentPaidBinding
import com.joelysondavid.lend.service.constants.LendConstants
import com.joelysondavid.lend.view.adapter.DebtorAdapter
import com.joelysondavid.lend.view.listener.DebtorListener
import com.joelysondavid.lend.viewmodel.LendViewModel

class PaidFragment : Fragment() {

    private lateinit var paidViewModel: LendViewModel
    private val mAdapter:DebtorAdapter= DebtorAdapter()
    private lateinit var mListener:DebtorListener

    private var _binding: FragmentPaidBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paidViewModel =
            ViewModelProvider(this).get(LendViewModel::class.java)

        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 1 - Obter a recycler view
        var recycler = root.findViewById<RecyclerView>(R.id.recycler_paid)

        // 2 - Definir o Layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir o adapter
        recycler.adapter = mAdapter

        mListener = object : DebtorListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, LendFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(LendConstants.DEBTORID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                paidViewModel.delete(id)
                paidViewModel.load(LendConstants.FILTER.PAIDS)
            }
        }

        mAdapter.attachListener(mListener)
        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        paidViewModel.load(LendConstants.FILTER.PAIDS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        paidViewModel.debtorsList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDebtors(it)
        })
    }
}