package com.chethan.demoproject

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.chethan.demoproject.view.ProductListAdapter

class ProductListFragment : Fragment() {

    private val repository:DataRepository = DataRepository(createWebService())
    private lateinit var productListModel: ProductViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        productListModel = ViewModelProviders.of(this, ViewModelFactory(repository)).get(ProductViewModel::class.java)
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)


        productListModel.getProducts()
        productListModel.listOfProducts.observe(this, Observer(function = fun(productList: List<ProductFamily>?) {
            productList?.let {

                var productListAdapter = ProductListAdapter(productList)
                recyclerView.adapter = productListAdapter
                productListAdapter.setItemClickListener(object : ProductListAdapter.ItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val newFragment = ProductDetailFragment.newInstance(productList.get(position))
                        val transaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.frag_container, newFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                })
            }
        }))
    }


}
