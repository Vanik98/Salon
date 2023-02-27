package com.appointmate.login

import android.content.Context
import com.appointmate.login.repository.LoginShearedRepository


interface GetBarberTokenUseCase {
    suspend fun saveToken(context: Context, token: String)
    suspend fun getToken(context: Context)
}

internal class GetBarberTokenUseCaseImpl(private val loginShearedRepository:LoginShearedRepository) : GetBarberTokenUseCase {
    override suspend fun saveToken(context: Context, token: String) {
        loginShearedRepository.saveToken(context, token)
    }

    override suspend fun getToken(context: Context) {
        loginShearedRepository.getToken(context)
    }
}