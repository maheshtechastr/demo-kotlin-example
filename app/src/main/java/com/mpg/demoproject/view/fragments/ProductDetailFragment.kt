package com.mpg.demoproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpg.demoproject.R
import com.mpg.demoproject.data.model.ProductFamily
import com.mpg.demoproject.view.adapters.ProductDetailAdapter
import kotlinx.android.synthetic.main.fragment_vehicle.view.*


class ProductDetailFragment : Fragment() {

    lateinit var product: ProductFamily

    companion object {
        const val KEY_PRODUCT = "KEY_PRODUCT"

        fun newInstance(productFamily: ProductFamily): ProductDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_PRODUCT, productFamily)
            val fragment =
                ProductDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { product = it.getSerializable(KEY_PRODUCT) as ProductFamily }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vehicle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        val productDetailAdapter = ProductDetailAdapter(product)
        view.recyclerView.adapter = productDetailAdapter

    }
}