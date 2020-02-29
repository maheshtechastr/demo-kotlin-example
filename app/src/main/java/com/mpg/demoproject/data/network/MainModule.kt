package com.mpg.demoproject.data.network

import com.mpg.demoproject.data.model.ProductFamily
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//val mainModule = module {
//
//    single { DataRepository(get()) }
//
//    single { createWebService() }
//
//    viewModel { ProductViewModel(get()) }
//
//}

fun createWebService(): NetWorkApi {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("http://mobcategories.s3-website-eu-west-1.amazonaws.com")
        .build()

    return retrofit.create(NetWorkApi::class.java)
}

interface NetWorkApi{

    @GET("/")
    fun getProducts(): Call<List<ProductFamily>>

}