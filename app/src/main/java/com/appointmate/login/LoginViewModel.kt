package com.appointmate.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appointmate.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sendSmsShearedUseCase: SendSmsUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase,
) : BaseViewModel() {
    private val _successCode  = MutableLiveData<Int?>()
    val successCode: LiveData<Int?>
        get() = _successCode

    fun sendSms(number:String) {
        viewModelScope.launch { sendSmsShearedUseCase.sendSms(number) }
    }

    fun verifyCode(code: Int) {
        viewModelScope.launch {
            _successCode.value =  verifyCodeUseCase.verifyCode(code)
        }
    }
}