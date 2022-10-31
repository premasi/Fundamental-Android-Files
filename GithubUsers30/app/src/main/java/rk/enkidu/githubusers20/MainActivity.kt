package rk.enkidu.githubusers20

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.getSystemService
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import rk.enkidu.githubusers20.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.githubusers20.data.helper.ViewModelFactory
import rk.enkidu.githubusers20.response.ItemsItem
import rk.enkidu.githubusers20.settings.SettingPreferences
import rk.enkidu.githubusers20.settings.SettingsActivity
import rk.enkidu.githubusers20.settings.SettingsModelFactory
import rk.enkidu.githubusers20.settings.SettingsViewModel

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding

    //ViewModel
    private lateinit var mainViewModel : MainViewModel

    //setting theme
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingTheme()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        showLoading(false)

        binding?.tvWelcome?.text = getString(R.string.welcomeback)

        mainViewModel = obtainViewModel(this@MainActivity)

        //set data search
        mainViewModel.item.observe(this) {
            showListUsers(it)
        }

        //show loading
        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun settingTheme(){
        val pref = SettingPreferences.getInstance(dataStore)
        val settingsViewModel = ViewModelProvider(this, SettingsModelFactory(pref))[SettingsViewModel::class.java]

        settingsViewModel.getThemeSettings().observe(this){
            if(it){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService<SearchManager>()
        val searchView = menu?.findItem(R.id.search_users)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                if (p0 != null) {
                    showText(false)
                    mainViewModel.searchUser(p0)

                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                showText(false)
                showRv(false)
                showLoading(true)

                //Home interface back to normal
                if (p0 == ""){
                    showText(false)
                    showRv(false)
                    showLoading(false)
                }
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.setting_theme -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showListUsers(items: List<ItemsItem>) {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
            applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_UNDEFINED
        ) {

            if(items.isEmpty()){
                Toast.makeText(this, getString(R.string.usernotfound), Toast.LENGTH_SHORT).show()
            } else {
                showLoading(false)
                showText(false)
                showRv(true)

                //recycle view
                binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
                val listUsersAdapter = ListUsersAdapter(items)
                binding?.rvUsers?.adapter = listUsersAdapter
            }
        }
    }

    private fun showLoading(isLoading: Boolean){ binding?.pb1?.visibility = if (isLoading) View.VISIBLE else View.GONE }

    private fun showRv(isShow: Boolean){binding?.rvUsers?.visibility = if(isShow) View.VISIBLE else View.GONE}

    private fun showText(isShow: Boolean){binding?.tvWelcome?.visibility = if(isShow) View.VISIBLE else View.GONE}


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}