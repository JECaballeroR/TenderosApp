package com.slabcode.agutierrezt.example2.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.slabcode.agutierrezt.example2.data.databases.dao.StoreDao
import com.slabcode.agutierrezt.example2.data.mocks.StoreInfoMock
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.models.StoreInfo
import com.slabcode.agutierrezt.example2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeRepository(private val dataSource: StoreDao,private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.STORE_COLLECTION)

    suspend fun loadStoreInfo(): StoreInfo {
//        return withContext(Dispatchers.IO) {
//            dataSource.getStore()!!
//        }
        val snapshot = db.get().await()
        return snapshot.toObjects(StoreInfo::class.java)[0]
    }

    suspend fun insertStore(store: StoreInfo) {
        withContext(Dispatchers.IO) {
            val temp = dataSource.getStore()
            if(temp == null)  {
                dataSource.insertStore(store)
            }
        }

    }

}