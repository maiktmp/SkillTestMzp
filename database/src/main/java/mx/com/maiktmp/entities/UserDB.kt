package mx.com.maiktmp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    val name: String? = null,

    val email: String? = null,
)