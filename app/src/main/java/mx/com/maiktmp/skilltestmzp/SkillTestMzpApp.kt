package mx.com.maiktmp.skilltestmzp

import android.app.Application
import mx.com.maiktmp.DBSkillTestMzp
import mx.com.maiktmp.skilltestmzp.utils.PreferenceHelper

class SkillTestMzpApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDatabase()
        initPreferences()
    }

    private fun initDatabase() {
        DBSkillTestMzp.createDatabase(this)
    }

    private fun initPreferences() {
        PreferenceHelper.initPreferences(this)
    }
}