package rk.enkidu.githubusers20

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.githubusers20.data.helper.ViewModelFactory
import rk.enkidu.githubusers20.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private var _binding : ActivityFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter : FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.title = getString(R.string.activity_favorite)

        mainViewModel = obtainViewModel(this@FavoriteActivity)

        mainViewModel.getAllUser().observe(this){
            if(it != null){
                adapter.setListFav(it)
                showLoading(false)
                binding?.tvFavorite?.visibility = View.GONE

                if(it.size <= 0){
                    binding?.tvFavorite?.visibility = View.VISIBLE
                    binding?.tvFavorite?.text = getString(R.string.favorite_empty)
                }
            }
        }

        adapter = FavoriteAdapter()

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_UNDEFINED){
            binding?.apply {
                rvUsersFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
                rvUsersFavorite.setHasFixedSize(true)
                rvUsersFavorite.adapter = adapter
            }

        }



    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean){ binding?.pb4?.visibility = if (isLoading) View.VISIBLE else View.GONE }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}