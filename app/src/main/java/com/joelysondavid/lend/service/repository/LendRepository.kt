package com.joelysondavid.lend.service.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import androidx.core.database.getDoubleOrNull
import androidx.core.database.getStringOrNull
import com.joelysondavid.lend.service.DataBaseConstants
import com.joelysondavid.lend.service.model.LendModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LendRepository private constructor(context: Context) {

    private var mLendDataBaseHelper: LendDataBaseHelper = LendDataBaseHelper(context)

    // Singleton
    companion object {
        private lateinit var lendRepository: LendRepository

        fun getInstance(context: Context): LendRepository {
            if (!::lendRepository.isInitialized) {
                lendRepository = LendRepository(context)
            }

            return lendRepository
        }
    }

    fun save(lend: LendModel): Boolean {
        return try {
            val db = mLendDataBaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.DEBTOR_NAME, lend.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.LOAN_DATE, lend.loanDate.toString())
            values.put(DataBaseConstants.GUEST.COLUMNS.LOAN_AMOUNT, lend.totalValue)
            values.put(DataBaseConstants.GUEST.COLUMNS.REMAINING_AMOUNT, lend.totalValue)
            values.put(DataBaseConstants.GUEST.COLUMNS.AMOUNT_PAID, lend.amountPaid)
            values.put(DataBaseConstants.GUEST.COLUMNS.LAST_PAYMENT, lend.lastPayment.toString())


            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun update(lend: LendModel): Boolean {
        return try {
            val db = mLendDataBaseHelper.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.DEBTOR_NAME, lend.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.AMOUNT_PAID, lend.amountPaid)
            values.put(DataBaseConstants.GUEST.COLUMNS.REMAINING_AMOUNT, lend.remainingAmount)
            values.put(DataBaseConstants.GUEST.COLUMNS.LAST_PAYMENT, lend.lastPayment.toString())

            val whereClause = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val whereArgs = arrayOf(lend.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, whereClause, whereArgs)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mLendDataBaseHelper.writableDatabase

            val whereClause = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val whereArgs = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, whereClause, whereArgs)
            true
        } catch (ex: Exception) {
            false
        }
    }

    fun getAll(): List<LendModel> {
        val list: MutableList<LendModel> = ArrayList()
        return list
    }

    fun getOwing(): List<LendModel> {
        val list: MutableList<LendModel> = ArrayList()
        return list
    }

    fun getPaids(): List<LendModel> {
        val list: MutableList<LendModel> = ArrayList()
        return list
    }

    @SuppressLint("SimpleDateFormat")
    fun getById(id: Int): LendModel? {
        var lend: LendModel? = null
        return try {

            val db = mLendDataBaseHelper.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.DEBTOR_NAME,
                DataBaseConstants.GUEST.COLUMNS.LOAN_DATE,
                DataBaseConstants.GUEST.COLUMNS.LOAN_AMOUNT,
                DataBaseConstants.GUEST.COLUMNS.AMOUNT_PAID,
                DataBaseConstants.GUEST.COLUMNS.REMAINING_AMOUNT,
                DataBaseConstants.GUEST.COLUMNS.LAST_PAYMENT,
            )

            val selection = "${DataBaseConstants.GUEST.COLUMNS.ID} = ?"
            val selectionArgs = arrayOf(id.toString())

            val cursor: Cursor =
                db.query(
                    DataBaseConstants.GUEST.TABLE_NAME,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
                )


            if (cursor.moveToNext()) {
                val debtorName =
                    cursor.getStringOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.DEBTOR_NAME))
                val loanDate =
                    cursor.getStringOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.LOAN_DATE))
                val loanAmount =
                    cursor.getDoubleOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.LOAN_AMOUNT))
                val amountPaid =
                    cursor.getDoubleOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.AMOUNT_PAID))
                val remainingAmount =
                    cursor.getDoubleOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.REMAINING_AMOUNT))
                val lastPayment =
                    cursor.getStringOrNull(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.LAST_PAYMENT))

                lend = LendModel(
                    id,
                    debtorName ?: "",
                    SimpleDateFormat("dd/MM/yyyy").parse(loanDate ?: "01/02/2003"),
                    loanAmount ?: 0.0,
                    amountPaid ?: 0.0,
                    remainingAmount ?: 0.0,
                    SimpleDateFormat("dd/MM/yyyy").parse(lastPayment ?: "01/02/2003")
                )
            }
            cursor.close()

            lend
        } catch (ex: Exception) {
            lend
        }
    }
}