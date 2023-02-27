package com.appointmate.login

import com.appointmate.login.repository.LoginShearedRepository

interface SendSmsUseCase {
    suspend fun sendSms(number: String)
}

internal class SendSmsUseCaseImpl(private val loginShearedRepository: LoginShearedRepository) : SendSmsUseCase {
    override suspend fun sendSms(number: String) {

    }
}