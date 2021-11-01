package com.joelysondavid.lend.view

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joelysondavid.lend.R
import com.joelysondavid.lend.databinding.ActivityLendFormBinding
import com.joelysondavid.lend.service.constants.LendConstants
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.viewmodel.LendFormViewModel
import kotlinx.android.synthetic.main.activity_lend_form.*
import com.joelysondavid.lend.utils.*
import kotlinx.android.synthetic.main.fragment_paid.*
import java.text.SimpleDateFormat

class LendFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var lendFormViewModel: LendFormViewModel
    private lateinit var binding: ActivityLendFormBinding

    private var mDebtorId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLendFormBinding.inflate(layoutInflater)
        title = getString(R.string.title_activity_form)
        setContentView(R.layout.activity_lend_form)
        lendFormViewModel = ViewModelProvider(this).get(LendFormViewModel::class.java)

        setListeners()
        observe()
        loadData()
    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.btn_save) {
            val name = edit_owing_name.text.toString()
            val date = edit_owing_date.text.toString()
            val totalValue = edit_total_value.text.toString()
            val paying = edit_payment_amount.text.toString()

            val lend =
                LendModel(
                    id = mDebtorId,
                    name = name,
                    loanDate = date,
                    totalValue = totalValue.toDouble(),
                    amountPaying = paying.toDouble()
                )

            lendFormViewModel.save(lend)
        }
    }

    private fun setListeners() {
        btn_save.setOnClickListener(this)

        edit_owing_date.addTextChangedListener(DateMask())
    }

    private fun observe() {
        lendFormViewModel.saveLoan.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_SHORT).show()
            }
        })

        lendFormViewModel.lend.observe(this, Observer {
            edit_owing_name.setText(it.name)
            edit_owing_date.setText(it.loanDate)
            edit_total_value.setText(it.totalValue.toString())
            text_remaining.text = it.remainingAmount.toString()

            hideEditFields(it.id != 0)
        })


    }

    private fun hideEditFields(isEdit: Boolean) {
        if (isEdit) {
            text_remaining.visibility = View.VISIBLE
            text_loan.visibility = View.VISIBLE
            text_payment_amount.visibility = View.VISIBLE
            edit_payment_amount.visibility = View.VISIBLE
            btn_save.text = getString(R.string.to_pay)
        } else {
            text_remaining.visibility = View.GONE
            text_loan.visibility = View.GONE
            text_payment_amount.visibility = View.GONE
            edit_payment_amount.visibility = View.GONE
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mDebtorId = bundle.getInt(LendConstants.DEBTORID)
            lendFormViewModel.load(mDebtorId)
        }
    }

}