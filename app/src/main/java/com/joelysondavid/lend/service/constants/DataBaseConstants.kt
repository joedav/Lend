package com.joelysondavid.lend.service.constants

class DataBaseConstants {
    /**
     * Tabelas dispiníveis no banco de dados com suas colunas
     */

    object GUEST {
        const val TABLE_NAME = "Lend"

        object COLUMNS {
            const val ID = "id"
            const val DEBTOR_NAME = "debtor_name"
            const val LOAN_DATE = "loan_date"
            const val LOAN_AMOUNT = "loan_amount"
            const val AMOUNT_PAID = "amount_paid"
            const val REMAINING_AMOUNT = "remaining_amount"
            const val LAST_PAYMENT = "last_payment"
        }
    }
}