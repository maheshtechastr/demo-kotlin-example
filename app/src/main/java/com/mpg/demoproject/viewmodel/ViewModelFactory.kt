package com.mpg.demoproject.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpg.demoproject.data.repository.DataRepository


class ViewModelFactory(val repository: DataRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass === ProductViewModel::class.java)
            return ProductViewModel(repository) as T
        return super.create(modelClass)
    }
}