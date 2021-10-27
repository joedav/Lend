package com.joelysondavid.lend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaidViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is paid Fragment"
    }
    val text: LiveData<String> = _text
}