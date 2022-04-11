package com.baha.kmfapp.di

import com.baha.kmfapp.data.repository.RegistrationRepository
import org.koin.dsl.module

val repoModule = module {
    single { RegistrationRepository(get()) }
}