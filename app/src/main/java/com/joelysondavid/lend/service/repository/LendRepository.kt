package com.joelysondavid.lend.service.repository

import android.content.Context
import com.joelysondavid.lend.service.model.LendModel

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

    fun save(lend: LendModel) {
        mLendDataBaseHelper.writableDatabase


    }

    fun update(lend: LendModel) {

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