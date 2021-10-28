package com.joelysondavid.lend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joelysondavid.lend.service.model.LendModel
import com.joelysondavid.lend.service.repository.LendRepository

class LendFormViewModel : ViewModel() {

    private val mLendRepository: LendRepository = LendRepository()
    private var mSaveLoan = MutableLiveData<Boolean>()
    val saveLoan: LiveData<Boolean> = mSaveLoan

    fun save(lend: LendModel) {
        mLendRepository.save(lend)
    }

}