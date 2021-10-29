package com.joelysondavid.lend.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.joelysondavid.lend.service.DataBaseConstants

class LendDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_LEND)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Lend.db"

        private const val CREATE_TABLE_LEND =
            "CREATE TABLE ${DataBaseConstants.GUEST.TABLE_NAME}" +
                    "(${DataBaseConstants.GUEST.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${DataBaseConstants.GUEST.COLUMNS.DEBTOR_NAME} TEXT," +
                    "${DataBaseConstants.GUEST.COLUMNS.LOAN_DATE} TEXT," +
                    "${DataBaseConstants.GUEST.COLUMNS.LOAN_AMOUNT} REAL," +
                    "${DataBaseConstants.GUEST.COLUMNS.REMAINING_AMOUNT} REAL," +
                    "${DataBaseConstants.GUEST.COLUMNS.AMOUNT_PAID} REAL," +
                    "${DataBaseConstants.GUEST.COLUMNS.LAST_PAYMENT} TEXT);"
    }
}