package rk.enkidu.githubusers20.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import rk.enkidu.githubusers20.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun getSavedUsers(): LiveData<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    suspend fun checkUser(username: String): Int

    @Delete
    suspend fun deleteUser(user: UserEntity)


}