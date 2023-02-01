package com.example.di

import com.example.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules by lazy {
    listOf(
        viewModelsModule
    )
}

private val viewModelsModule = module {
    viewModel {
        MainViewModel()
    }
}