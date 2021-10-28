package com.joelysondavid.lend.service.model

import java.util.*

data class LendModel(
    var name: String,
    var date: Date,
    var totalValue: Double,
    var paymentMethod: Double = 0.0
) {

}