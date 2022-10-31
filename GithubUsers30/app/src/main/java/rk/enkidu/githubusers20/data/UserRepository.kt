package rk.enkidu.githubusers20.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rk.enkidu.githubusers20.data.entity.UserEntity
import rk.enkidu.githubusers20.data.room.UserDao
import rk.enkidu.githubusers20.data.room.UserDatabase

class UserRepository(application: Application){
    private val userDao: UserDao

    init {
        val database = UserDatabase.getInstance(application)
        userDao = database.userDao()
    }

    fun insertFavorite(user: UserEntity){
        CoroutineScope(Dispatchers.IO).launch{
            userDao.insertUser(user)
        }
    }

    suspend fun checkUser(username: String) = userDao.checkUser(username)


    fun deleteFavorite(user: UserEntity){
        CoroutineScope(Dispatchers.IO).launch {
            userDao.deleteUser(user)
        }
    }

    fun getSaved(): LiveData<List<UserEntity>> = userDao.getSavedUsers()




}
