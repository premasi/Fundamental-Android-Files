package rk.enkidu.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.Exception

class ProfileDetail : AppCompatActivity() {

    companion object{
        const val EXTRA_USERS = "extra_users"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        try {

            val ivAvatar: ImageView = findViewById(R.id.iv_avatar_detail)
            val tvUsername: TextView = findViewById(R.id.tv_username_detail)
            val tvName: TextView = findViewById(R.id.tv_name_detail)
            val tvFollower: TextView = findViewById(R.id.tv_follower)
            val tvFollowing: TextView = findViewById(R.id.tv_following)
            val tvCompany: TextView = findViewById(R.id.tv_company_detail)
            val tvLocation: TextView = findViewById(R.id.tv_location_detail)
            val tvRepository: TextView = findViewById(R.id.tv_repository_detail)
            val btnClose: Button = findViewById(R.id.btn_close)
            val user = intent.getParcelableExtra<Users>(EXTRA_USERS) as Users

            if (user != null) {


                ivAvatar.setImageResource(user.avatar)
                tvUsername.text = user.username
                tvName.text = user.name
                tvCompany.text = user.company
                tvFollower.text = user.follower
                tvFollowing.text = user.following
                tvLocation.text = user.location
                tvRepository.text = user.repository

                btnClose.setOnClickListener {
                    finish()
                }
            }
        } catch (e: Exception){
            e.stackTrace
        }




    }
}