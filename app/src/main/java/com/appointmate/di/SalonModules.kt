package com.appointmate.di

import com.appointmate.home.barberqueuelist.BarberQueueViewModel
import com.appointmate.login.LoginViewModel
import com.appointmate.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val salonModules by lazy {
    listOf(
        mainModule,
        loginModule,
        barberQueueListModule
    )
}

private val mainModule = module {
    viewModel {
        MainViewModel()
    }
}

private val loginModule = module{
    viewModel{LoginViewModel(get(),get())}
}

private val barberQueueListModule = module{
    viewModel{ BarberQueueViewModel() }
}