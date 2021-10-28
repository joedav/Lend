package com.joelysondavid.lend.service

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
        }
    }
}