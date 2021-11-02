package com.joelysondavid.lend.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.rotationMatrix
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.databinding.FragmentAllBinding
import com.joelysondavid.lend.service.constants.LendConstants
import com.joelysondavid.lend.view.adapter.DebtorAdapter
import com.joelysondavid.lend.view.listener.DebtorListener
import com.joelysondavid.lend.viewmodel.LendViewModel

class AllFragment : Fragment() {

    private lateinit var allViewModel: LendViewModel

    private val mAdapter: DebtorAdapter = DebtorAdapter()
    private lateinit var mListener: DebtorListener

    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allViewModel =
            ViewModelProvider(this).get(LendViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 1 - Obter a recycler view
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all)

        // 2 - Definir o layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter
        recycler.adapter= mAdapter

        mListener = object : DebtorListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, LendFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(LendConstants.DEBTORID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                allViewModel.delete(id)
                allViewModel.load(LendConstants.FILTER.ALL)
            }
        }

        mAdapter.attachListener(mListener)

        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        allViewModel.load(LendConstants.FILTER.ALL)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        allViewModel.debtorsList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDebtors(it)
        })
    }

}