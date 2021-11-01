package com.joelysondavid.lend.view.viewholder

import android.app.AlertDialog
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

        textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(itemView.context.getString(R.string.remove_debtor))
                .setMessage(itemView.context.getString(R.string.really_want_remove))
                .setPositiveButton(R.string.remove) { dialog, which ->
                    listener.onDelete(lendModel.id)
                }
                .setNeutralButton(itemView.context.getString(R.string.cancel), null)
                .show()
            true
        }
    }

}