package mx.com.maiktmp.skilltestmzp.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sp: SharedPreferences

    fun initPreferences(context: Context) {
        sp = context.getSharedPreferences("SkillTestMzpPreferences", Context.MODE_PRIVATE)
    }

    fun storeString(key: KEY, value: String?) {
        sp.edit().apply {
            putString(key.name, value)
            apply()
        }
    }


    fun retrievePreference(key: KEY): String? {
        return sp.getString(key.name, null)
    }


    enum class KEY(name: String) {
        EMPLOYEES_CACHE("EMPLOYEES_CACHE")
    }
}