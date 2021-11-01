package com.joelysondavid.lend.view.viewholder

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joelysondavid.lend.R
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.view.listener.DebtorListener

class DebtorViewHolder(itemView: View, private val listener: DebtorListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(lendModel: LendModel) {
        val textName = itemView.findViewById<TextView>(R.id.txt_debtor_name)
        textName.text = lendModel.name

        textName.setOnClickListener {
            listener.onClick(lendModel.id)
        }
    }

}