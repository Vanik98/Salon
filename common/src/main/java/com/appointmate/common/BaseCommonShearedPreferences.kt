package com.appointmate.common

import android.content.Context
import android.content.SharedPreferences

abstract class BaseCommonShearedPreferences {
    companion object{
        private const val SHARED_PREFS = "sharedPrefs"
    }
    fun saveData(context: Context,key:String,text:String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, text)
        editor.apply()
    }
    fun loadData(context: Context,key:String): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }
}