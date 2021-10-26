package com.joelysondavid.lend.ui.woing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joelysondavid.lend.databinding.FragmentOwingBinding

class OwingFragment : Fragment() {

    private lateinit var owingViewModel: OwingViewModel
    private var _binding: FragmentOwingBinding? = null

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

        val textView: TextView = binding.textOwing
        owingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}