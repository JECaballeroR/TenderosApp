package com.slabcode.agutierrezt.example2.di

import com.slabcode.agutierrezt.example2.data.repositories.CommentRepository
import com.slabcode.agutierrezt.example2.data.repositories.HomeRepository
import com.slabcode.agutierrezt.example2.data.repositories.ProductRepository
import com.slabcode.agutierrezt.example2.data.repositories.UserRepository
import org.koin.core.scope.get
import org.koin.dsl.module

val repoModule = module {
    single { HomeRepository(get(), get()) }
    single { CommentRepository(get(), get()) }
    single { ProductRepository(get(), get()) }
    single { UserRepository(get(), get()) }
}