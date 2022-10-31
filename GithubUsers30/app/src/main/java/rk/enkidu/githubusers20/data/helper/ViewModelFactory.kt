package rk.enkidu.githubusers20.data.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rk.enkidu.githubusers20.DetailViewModel
import rk.enkidu.githubusers20.MainViewModel

class ViewModelFactory private constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        } else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (instance == null) {
                synchronized(ViewModelFactory::class.java) {
                    instance = ViewModelFactory(application)
                }
            }
            return instance as ViewModelFactory
        }
    }
}