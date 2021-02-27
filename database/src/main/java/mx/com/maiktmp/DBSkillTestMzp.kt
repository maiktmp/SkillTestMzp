package mx.com.maiktmp

import android.content.Context
import androidx.room.*
import mx.com.maiktmp.dao.UserDao
import mx.com.maiktmp.entities.UserDB

@Database(
    entities = [
        UserDB::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DBSkillTestMzp : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        lateinit var db: DBSkillTestMzp

        public fun createDatabase(context: Context) {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBSkillTestMzp::class.java,
                    "DBSkillTestMzp.db"
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                instance
            }
        }
    }
}