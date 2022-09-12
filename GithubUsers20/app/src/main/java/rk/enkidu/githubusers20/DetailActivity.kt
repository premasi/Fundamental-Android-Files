package rk.enkidu.githubusers20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import rk.enkidu.githubusers20.databinding.ActivityDetailBinding
import rk.enkidu.githubusers20.pager.SectionsPagerAdapter
import rk.enkidu.githubusers20.response.ItemsItem

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private var userDetail : ItemsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show tab layout
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f


        //send data to DetailFragment
        userDetail = intent.getParcelableExtra<ItemsItem>(LOGIN) as ItemsItem
        TEMP_USERNAME = userDetail!!.login.toString()

    }

    //temp function to send username
    fun getTempDataUsername(): String{
        return TEMP_USERNAME
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