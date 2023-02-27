package com.appointmate.login.repository

import android.content.Context

internal interface LoginShearedRepository {
    fun saveToken(context: Context, token:String)
    fun getToken(context:Context) : String?
}

internal class LoginShearedRepositoryImp(private val tokenShearedPreferences: TokenShearedPreferences) : LoginShearedRepository {
    override fun saveToken(context: Context, token: String) {
        tokenShearedPreferences.saveToken(context,token)
    }

    override fun getToken(context: Context) : String? {
       return tokenShearedPreferences.getToken(context)
    }
}