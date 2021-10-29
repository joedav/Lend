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

    fun save(lend: LendModel) {
        mSaveLoan.value = mLendRepository.save(lend)
    }

}