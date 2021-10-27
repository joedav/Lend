package com.joelysondavid.lend.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.joelysondavid.lend.viewmodel.LendFormViewModel
import com.joelysondavid.lend.R

class LendFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: LendFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lend_form)
        title = getString(R.string.title_activity_form)

        mViewModel = ViewModelProvider(this).get(LendFormViewModel::class.java)

        setListeners()
    }

    private fun setListeners() {

    }

    private fun hideEditFields() {

    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.btn_save) {

        }
    }
}