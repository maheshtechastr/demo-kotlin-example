/**
 * @Author $Mahesh Gupta
 * @class AppDatabase
 * @date 01-Mar-2020
 * */

package com.mpg.demoproject.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.mpg.demoproject.data.model.Products

/**
 * Products represents the products fetched from the network
 */
//@Entity
//data class Products constructor(val products: String, @PrimaryKey val id: Int = 0)

/***
 * Very small database that will hold one products
 */
@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: Products)

    @get:Query("select * from Products where id = 0")
    val productsLiveData: LiveData<Products?>
}

/**
 * ProductsDatabase provides a reference to the dao to repositories
 */
@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
}

private lateinit var INSTANCE: ProductsDatabase

/**
 * Instantiate a database from a context.
 */
fun getDatabase(context: Context, dbName: String): ProductsDatabase {
    synchronized(ProductsDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    dbName
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
