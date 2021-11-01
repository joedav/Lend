package com.joelysondavid.lend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.service.repository.LendRepository

class OwingViewModel(application: Application) : AndroidViewModel(application) {

    private val mLendRepository = LendRepository.getInstance(application.applicationContext)

    private val mDebtorsList = MutableLiveData<List<LendModel>>()
    val debtorsList: LiveData<List<LendModel>> = mDebtorsList

    fun load() {
        mDebtorsList.value = mLendRepository.getOwing()
    }

    fun delete(id: Int) {
        mLendRepository.delete(id)
    }
}