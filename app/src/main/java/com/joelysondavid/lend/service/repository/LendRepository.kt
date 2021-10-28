package com.joelysondavid.lend.service.repository

import com.joelysondavid.lend.service.model.LendModel

class LendRepository {
    fun save(lend: LendModel) {

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