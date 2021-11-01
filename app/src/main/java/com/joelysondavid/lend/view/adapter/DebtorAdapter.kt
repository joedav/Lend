package com.joelysondavid.lend.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.view.viewholder.DebtorViewHolder

class DebtorAdapter : RecyclerView.Adapter<DebtorViewHolder>() {
    private var mDebtorList: List<LendModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtorViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_debtor, parent, false)

        return DebtorViewHolder(item)
    }

    override fun onBindViewHolder(holder: DebtorViewHolder, position: Int) {
        holder.bind(mDebtorList[position])
    }

    override fun getItemCount(): Int {
        return mDebtorList.count()
    }

    fun updateDebtors(list: List<LendModel>) {
        mDebtorList = list
        notifyDataSetChanged()
    }

}