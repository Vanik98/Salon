package com.appointmate.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appointmate.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sendSmsUseCase: SendSmsUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : BaseViewModel() {
    private var _successCode  = MutableLiveData<Int?>()
    val successCode: LiveData<Int?>
        get() = _successCode

    fun sendSms(number:String) {
        viewModelScope.launch { sendSmsUseCase.sendSms(number) }
    }

    fun verifyCode(code: Int) {
        viewModelScope.launch {
            _successCode.value =  verifyCodeUseCase.verifyCode(code)
        }
    }
}