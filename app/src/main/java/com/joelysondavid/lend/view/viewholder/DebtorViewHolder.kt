package com.joelysondavid.lend.view.viewholder

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.service.model.LendModel

class DebtorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(lendModel: LendModel) {
        val textName = itemView.findViewById<TextView>(R.id.txt_debtor_name)
        textName.text = lendModel.name
    }

}