package rk.enkidu.githubusers20.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "users")
data class UserEntity(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @field:ColumnInfo(name = "username")
    var username: String,

    @field:ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

) : Serializable