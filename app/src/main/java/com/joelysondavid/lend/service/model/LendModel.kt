package com.joelysondavid.lend.service.model

import java.util.*

data class LendModel(
    var id: Int = 0,
    var name: String,
    var loanDate: String,
    var totalValue: Double,
    var amountPaid: Double = 0.0,
    var remainingAmount: Double = totalValue,
    var lastPayment: String? = null,
) {

}