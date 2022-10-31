package rk.enkidu.githubusers20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rk.enkidu.githubusers20.data.entity.UserEntity
import rk.enkidu.githubusers20.data.helper.ViewModelFactory
import rk.enkidu.githubusers20.databinding.ActivityDetailBinding
import rk.enkidu.githubusers20.pager.SectionsPagerAdapter
import rk.enkidu.githubusers20.response.ItemsItem

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding
    private var userDetail : ItemsItem? = null

    //ViewModel
    private lateinit var detailViewModel : DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        detailViewModel = obtainViewModel(this)

        //show tab layout
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding?.viewPager?.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding?.tabs!!, binding?.viewPager!!) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        //send data to DetailFragment
        userDetail = intent.getParcelableExtra<ItemsItem>(LOGIN) as ItemsItem
        TEMP_USERNAME = userDetail!!.login.toString()

        //set data
        val userTemp = UserEntity(
            userDetail!!.id!!,
            userDetail?.login!!,
            userDetail?.avatarUrl!!
        )

        var check = false
        CoroutineScope(Dispatchers.IO).launch {
            val countByUsername = detailViewModel.checkUser(userTemp.username)
            withContext(Dispatchers.Main){
                if(countByUsername > 0){
                    binding?.toggleFav?.isChecked = true
                    check = true
                } else {
                    binding?.toggleFav?.isChecked = false
                    check = false
                }
            }
        }

        binding?.toggleFav?.setOnClickListener {
            check = !check
            if(check){
                detailViewModel.insertFavorite(userTemp)
                Toast.makeText(this, userTemp.username + " added to favorite", Toast.LENGTH_SHORT).show()
            } else {
                detailViewModel.deletefavorite(userTemp)
                Toast.makeText(this, userTemp.username + " remove from favorite", Toast.LENGTH_SHORT).show()
            }
            binding?.toggleFav?.isChecked = check
        }



    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DetailViewModel::class.java]
    }

    //temp function to send username
    fun getTempDataUsername(): String{
        return TEMP_USERNAME
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )

        const val LOGIN = "login"
        var TEMP_USERNAME = "temp"
    }

}