package com.slabcode.agutierrezt.example2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoreInfo(
    @PrimaryKey var id: Int? = null,
    var name: String? = null,
    var image: String? = null,
    var address: String? = null,
    var description: String? = null,
    var lat: Double? = null,
    var lng: Double? = null
)