package mx.com.maiktmp.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import mx.com.maiktmp.entities.UserDB

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: UserDB): Completable

    @Query("SELECT * FROM user LIMIT 1")
    fun findActive(): Single<List<UserDB>>

    @Delete
    fun deleteUser(category: UserDB): Completable

}