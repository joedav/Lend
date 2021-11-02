package com.joelysondavid.lend.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.service.repository.LendRepository

class LendFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mLendRepository: LendRepository =
        LendRepository.getInstance(application.applicationContext)

    private var mSaveLoan = MutableLiveData<Boolean>()
    val saveLoan: LiveData<Boolean> = mSaveLoan

    private var mLend = MutableLiveData<LendModel>()
    val lend: LiveData<LendModel> = mLend

    fun save(lend: LendModel) {
        if (lend.id == 0)
            mSaveLoan.value = mLendRepository.save(lend)
        else {
            toPay(lend)
            mSaveLoan.value = mLendRepository.update(lend)
        }
    }

    fun load(id: Int) {
        mLend.value = mLendRepository.getById(id)
    }

    private fun toPay(lend: LendModel): LendModel {
        if (lend.amountPaying != .0&&lend.amountPaying<=lend.remainingAmount) {
            lend.remainingAmount = lend.remainingAmount - lend.amountPaying
        }

        return lend
    }
}