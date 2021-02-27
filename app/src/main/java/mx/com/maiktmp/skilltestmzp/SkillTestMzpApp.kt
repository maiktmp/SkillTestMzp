package mx.com.maiktmp.skilltestmzp

import android.app.Application
import mx.com.maiktmp.skilltestmzp.base.di.ApplicationComponent
import mx.com.maiktmp.skilltestmzp.base.di.DaggerApplicationComponent

class SkillTestMzpApp : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        applicationComponent =
            DaggerApplicationComponent
                .builder()
                .build()
    }
}