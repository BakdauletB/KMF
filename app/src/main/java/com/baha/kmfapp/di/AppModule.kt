package com.baha.kmfapp.di

import com.baha.kmfapp.presentation.activity.RegistrationViewModel
import com.baha.kmfapp.presentation.fragment.ShowInfoVM
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { RegistrationViewModel() }
    viewModel { ShowInfoVM(androidApplication()) }

}