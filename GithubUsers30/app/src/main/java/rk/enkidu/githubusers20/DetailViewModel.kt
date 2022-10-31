package rk.enkidu.githubusers20

import android.app.Application
import androidx.lifecycle.*
import rk.enkidu.githubusers20.data.UserRepository
import rk.enkidu.githubusers20.data.entity.UserEntity

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository : UserRepository = UserRepository(application)

    //insert favorite
    fun insertFavorite(userEntity: UserEntity) = userRepository.insertFavorite(userEntity)

    //check user in database
    suspend fun checkUser(username: String) = userRepository.checkUser(username)

    //delete user
    fun deletefavorite(userEntity: UserEntity) = userRepository.deleteFavorite(userEntity)
}