package rk.enkidu.githubusers

//Created by : Raka Ryandra Guntara

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser : RecyclerView
    private val list = ArrayList<Users>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_users)
        rvUser.setHasFixedSize(true)

        list.addAll(listUsers)
        showUser()

    }

    private val listUsers : ArrayList<Users>
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataPhoto = resources.obtainTypedArray(R.array.avatar)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val listUser = ArrayList<Users>()

            for(i in dataUsername.indices){
                val user = Users(dataUsername[i], dataName[i], dataPhoto.getResourceId(i, -1)
                    ,dataFollowers[i], dataFollowing[i], dataCompany[i], dataLocation[i], dataRepository[i])
                listUser.add(user)

            }

            return listUser
        }

    private fun showUser(){

        if(applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ||
                applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
                applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_UNDEFINED) {

            rvUser.layoutManager = LinearLayoutManager(this)
            val listUsersAdapter = ListUsersAdapter(list)
            rvUser.adapter = listUsersAdapter

            listUsersAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Users) {
                    val intent = Intent(this@MainActivity, ProfileDetail::class.java)
                    intent.putExtra(ProfileDetail.EXTRA_USERS, data)
                    startActivity(intent)
                }
            })
        }
    }

}