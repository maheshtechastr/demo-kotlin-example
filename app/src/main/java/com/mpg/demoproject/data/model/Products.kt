package com.mpg.demoproject.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
/**
 * @Author $Mahesh Gupta

 * */
@Entity
data class Products(
    @PrimaryKey var id: Int,
    var categoryId: Int,
    var name: String,
    var url: String,
    var description: String,
    @Embedded var salePrice: SalePrice
) : Serializable