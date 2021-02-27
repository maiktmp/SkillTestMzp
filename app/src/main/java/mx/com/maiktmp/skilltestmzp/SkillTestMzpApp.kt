package mx.com.maiktmp.skilltestmzp

import android.app.Application
import mx.com.maiktmp.DBSkillTestMzp

class SkillTestMzpApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initDatabase()
    }

    private fun initDatabase() {
        DBSkillTestMzp.createDatabase(this)
    }
}