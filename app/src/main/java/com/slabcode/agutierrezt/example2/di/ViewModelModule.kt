package com.slabcode.agutierrezt.example2.di

import com.slabcode.agutierrezt.example2.ui.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CommentViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { SplashViewModel(get(),get(),get(), get()) }
    viewModel { LoginViewModel(get()) }
}