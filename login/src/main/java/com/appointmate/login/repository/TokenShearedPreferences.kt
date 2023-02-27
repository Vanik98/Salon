package com.appointmate.login.repository

import android.content.Context
import com.appointmate.common.BaseCommonShearedPreferences

internal class TokenShearedPreferences : BaseCommonShearedPreferences() {
    companion object{
        const val BARBER_TOKEN = "barber_token"
    }
    fun saveToken(context:Context,token:String) = saveData(context,BARBER_TOKEN,token)

    fun getToken(context:Context) = loadData(context,BARBER_TOKEN)
}