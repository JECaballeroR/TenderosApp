package com.slabcode.agutierrezt.example2.data.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.slabcode.agutierrezt.example2.data.models.Comment
import com.slabcode.agutierrezt.example2.data.models.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProducts(products: List<Product>)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>
}