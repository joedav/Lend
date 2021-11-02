package com.joelysondavid.lend.service.constants

class LendConstants private constructor() {
    companion object {
        const val DEBTORID = "debtorID"
        const val DEBTOREXTRA = "debtorExtra"
    }

    object FILTER{
        const val DEBTORS = 0
        const val PAIDS = 1
        const val ALL = 2
    }
}