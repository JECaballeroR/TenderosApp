package com.slabcode.agutierrezt.example2.data.mocks

import com.slabcode.agutierrezt.example2.data.models.Product

class ProductMock {
    fun loadProducts(): List<Product> {
        return listOf(
            Product(1,"https://www.latinfoodus.com/wp-content/uploads/2017/11/chocoRamo_mini.jpg","Producto 1", "$5.000", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Product(2,"https://pngimg.com/uploads/burger_sandwich/burger_sandwich_PNG96777.png","Producto 2", "$10.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Product(3,"https://www.latinfoodus.com/wp-content/uploads/2017/11/chocoRamo_mini.jpg","Producto 3", "$5.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
            Product(4,"https://pngimg.com/uploads/burger_sandwich/burger_sandwich_PNG96777.png","Producto 4", "$10.000","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"),
        )
    }
}