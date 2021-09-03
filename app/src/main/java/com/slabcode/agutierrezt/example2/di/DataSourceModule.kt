package com.slabcode.agutierrezt.example2.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.slabcode.agutierrezt.example2.data.mocks.CommentMock
import com.slabcode.agutierrezt.example2.data.mocks.ProductMock
import com.slabcode.agutierrezt.example2.data.mocks.StoreInfoMock
import org.koin.dsl.module

val dataSourceModule = module {
    single { StoreInfoMock() }
    single { CommentMock() }
    single { ProductMock() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance().getReference() }
}