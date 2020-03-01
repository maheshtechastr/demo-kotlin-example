package com.mpg.demoproject.data.model

import java.io.Serializable

/**
 * @Author $Mahesh Gupta
 * @class ProductFamily
 * @date 28-Feb-2020
 * */
data class ProductFamily(
    var id: Int,
    var products: List<Products>,
    var name: String,
    var description: String,
    var totalPages: Int
) : Serializable