package com.slabcode.agutierrezt.example2.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.slabcode.agutierrezt.example2.data.databases.dao.ProductDao
import com.slabcode.agutierrezt.example2.data.mocks.ProductMock
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.models.Product
import com.slabcode.agutierrezt.example2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductRepository(private val dataSource: ProductDao, private val dataSourceFirebase: FirebaseFirestore) {
    private val db: CollectionReference = dataSourceFirebase.collection(Constants.PRODUCT_COLLECTION)

    suspend fun loadProducts(): List<Product> {
//        return withContext(Dispatchers.IO) {
//            dataSource.getAllProducts()
//        }
        val snapshot = db.get().await()
        return snapshot.toObjects(Product::class.java)
    }

    suspend fun insertProducts(products: List<Product>) {
        withContext(Dispatchers.IO) {
            val temp = dataSource.getAllProducts()
            if(temp.isEmpty()) {
                dataSource.insertProducts(products)
            }
        }
    }

}