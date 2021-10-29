package com.joelysondavid.lend.service.model

import java.util.*

data class LendModel(
    var id: Int,
    var name: String,
    var loanDate: Date,
    var totalValue: Double,
    var lastPayment: Date,
    var amountPaid: Double = 0.0,
    var remainingAmount: Double = totalValue,
) {

}