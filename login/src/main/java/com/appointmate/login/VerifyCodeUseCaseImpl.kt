package com.appointmate.login

import com.appointmate.login.repository.LoginShearedRepository

interface VerifyCodeUseCase {
    suspend fun verifyCode(code: Int): Int?
}

internal class VerifyCodeUseCaseImpl(private val loginShearedRepository: LoginShearedRepository) : VerifyCodeUseCase {
    override suspend fun verifyCode(code: Int): Int? {
        return 1
    }
}