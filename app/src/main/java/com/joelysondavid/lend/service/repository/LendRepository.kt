package com.joelysondavid.lend.service.repository

import android.content.ContentValues
import android.content.Context
import com.joelysondavid.lend.service.DataBaseConstants
import com.joelysondavid.lend.service.model.LendModel
import java.lang.Exception

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

    fun delete(id: Int) {

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

    fun getById(id: Int): Boolean {
        return false
    }
}