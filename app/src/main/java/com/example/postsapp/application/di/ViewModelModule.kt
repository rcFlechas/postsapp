package com.example.postsapp.application.di

import com.example.postsapp.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
        viewModel { UserViewModel(get()) }
}
