package rk.enkidu.githubusers20

import android.app.SearchManager
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.core.content.getSystemService
import rk.enkidu.githubusers20.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.githubusers20.response.ItemsItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsers.setHasFixedSize(true)
        showLoading(false)

        binding.tvWelcome.text = "Welcome back!"

        //set data search
        mainViewModel.item.observe(this) {
            showListUsers(it)
        }

        //show loading
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService<SearchManager>()
        val searchView = menu?.findItem(R.id.search_users)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        searchView.queryHint = "Raka Ganteng"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (p0 != null) {
                    binding.tvWelcome.text = ""
                    mainViewModel.searchUser(p0)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                binding.tvWelcome.text = ""
                binding.rvUsers.visibility = View.INVISIBLE
                showLoading(true)
                return false
            }

        })

        return true
    }

    private fun showListUsers(items: List<ItemsItem>) {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_UNDEFINED
        ) {
            binding.rvUsers.visibility = View.VISIBLE
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
            val listUsersAdapter = ListUsersAdapter(items)
            listUsersAdapter.notifyDataSetChanged()
            binding.rvUsers.adapter = listUsersAdapter

            listUsersAdapter.setOnItemClicked(object : ListUsersAdapter.OnItemClickCallBack {
                override fun onClicked(data: ItemsItem) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.LOGIN, data)
                    startActivity(intent)
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pb1.visibility = View.VISIBLE
        } else {
            binding.pb1.visibility = View.GONE
        }
    }

//    companion object{
//        val TAG = MainActivity::class.java.simpleName
//        const val TOKEN = "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"
//    }

}