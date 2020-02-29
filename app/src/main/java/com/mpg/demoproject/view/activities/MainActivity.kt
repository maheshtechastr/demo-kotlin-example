package com.mpg.demoproject.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mpg.demoproject.R
import com.mpg.demoproject.view.fragments.ProductListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container,
                ProductListFragment()
            ).commit()
    }
}
