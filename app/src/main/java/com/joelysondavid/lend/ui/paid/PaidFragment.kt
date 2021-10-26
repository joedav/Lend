package com.joelysondavid.lend.ui.paid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joelysondavid.lend.databinding.FragmentPaidBinding

class PaidFragment : Fragment() {

    private lateinit var paidViewModel: PaidViewModel
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
            ViewModelProvider(this).get(PaidViewModel::class.java)

        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPaid
        paidViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}