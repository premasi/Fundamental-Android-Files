package rk.enkidu.githubusers

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val username: String,
    val name: String,
    val avatar: Int,
    val follower: String,
    val following: String,
    val company: String,
    val location: String,
    val repository: String
                 ): Parcelable
