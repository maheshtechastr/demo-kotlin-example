package com.chethan.demoproject

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory(val repository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass === ProductViewModel::class.java)
            return ProductViewModel(repository) as T
        return super.create(modelClass)
    }
}