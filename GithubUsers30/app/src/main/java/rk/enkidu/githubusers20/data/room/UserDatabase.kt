package rk.enkidu.githubusers20.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rk.enkidu.githubusers20.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var instance : UserDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): UserDatabase {
            if(instance == null){
                synchronized(UserDatabase::class.java){
                    instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, "user_database").build()
                }
            }

            return instance as UserDatabase
        }


    }
}