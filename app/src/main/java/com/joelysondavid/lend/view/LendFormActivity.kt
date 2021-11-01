package com.joelysondavid.lend.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joelysondavid.lend.R
import com.joelysondavid.lend.databinding.ActivityLendFormBinding
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.viewmodel.LendFormViewModel
import kotlinx.android.synthetic.main.activity_lend_form.*
import com.joelysondavid.lend.utils.*
import java.text.SimpleDateFormat

class LendFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var lendFormViewModel: LendFormViewModel
    private lateinit var binding: ActivityLendFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLendFormBinding.inflate(layoutInflater)
        title = getString(R.string.title_activity_form)
        setContentView(R.layout.activity_lend_form)
        lendFormViewModel = ViewModelProvider(this).get(LendFormViewModel::class.java)

        setListeners()
        observe()
        hideEditFields(false)
    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.btn_save) {
            val name = edit_owing_name.text.toString()
            val date = edit_owing_date.text.toString()
            val totalValue = edit_total_value.text.toString()

            val dateFormatted = SimpleDateFormat("dd/MM/yyyy").parse(date)

            val lend =
                LendModel(
                    name = name,
                    loanDate = dateFormatted.toString(),
                    totalValue = totalValue.toDouble()
                )

            lendFormViewModel.save(lend)
        }
        hideEditFields(true)
    }

    private fun setListeners() {
        btn_save.setOnClickListener(this)
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
    }

    private fun hideEditFields(isEdit: Boolean) {
        if (isEdit) {
            text_remaining.visibility = View.VISIBLE
            text_loan.visibility = View.VISIBLE
            text_payment_amount.visibility = View.VISIBLE
            edit_payment_amount.visibility = View.VISIBLE
        } else {
            text_remaining.visibility = View.GONE
            text_loan.visibility = View.GONE
            text_payment_amount.visibility = View.INVISIBLE
            edit_payment_amount.visibility = View.INVISIBLE
        }
    }

}