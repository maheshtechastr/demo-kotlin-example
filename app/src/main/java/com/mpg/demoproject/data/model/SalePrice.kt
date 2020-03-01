package com.mpg.demoproject.data.model

import java.io.Serializable

/**
 * @Author $Mahesh Gupta
 *
 * */
data class SalePrice(

    var amount: Float,
    var currency: String
) : Serializable