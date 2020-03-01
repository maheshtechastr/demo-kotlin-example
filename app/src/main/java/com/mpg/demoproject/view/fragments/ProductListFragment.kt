package com.mpg.demoproject.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpg.demoproject.R
import com.mpg.demoproject.data.model.ProductFamily
import com.mpg.demoproject.data.network.createWebService
import com.mpg.demoproject.data.repository.DataRepository
import com.mpg.demoproject.view.adapters.ProductListAdapter
import com.mpg.demoproject.viewmodel.ProductViewModel
import com.mpg.demoproject.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_vehicle_list.*

class ProductListFragment : Fragment() {

    private val repository: DataRepository = DataRepository(createWebService())
    private lateinit var productListModel: ProductViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productListModel = ViewModelProviders.of(
            this,
            ViewModelFactory(repository)
        ).get(ProductViewModel::class.java)
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        recyclerView!!.layoutManager =
            LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)

        productListModel.getProducts()
        productListModel.listOfProducts.observe(
            this,
            Observer(function = fun(productList: List<ProductFamily>?) {
                productList?.let {

                    val productListAdapter =
                        ProductListAdapter(
                            productList
                        )
                    recyclerView.adapter = productListAdapter
                    productListAdapter.setItemClickListener(object :
                        ProductListAdapter.ItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            val newFragment =
                                ProductDetailFragment.newInstance(productList.get(position))
                            val transaction = fragmentManager!!.beginTransaction()
                            transaction.replace(R.id.frag_container, newFragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                    })
                }
            })
        )
    }


}
