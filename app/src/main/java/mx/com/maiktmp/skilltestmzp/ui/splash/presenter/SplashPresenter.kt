package mx.com.maiktmp.skilltestmzp.ui.splash.presenter

import mx.com.maiktmp.skilltestmzp.base.BasePresenter
import mx.com.maiktmp.skilltestmzp.ui.employee.data.UserRepository
import mx.com.maiktmp.skilltestmzp.ui.splash.data.SplashRepository
import mx.com.maiktmp.skilltestmzp.ui.splash.view.interfaces.SplashView

class SplashPresenter(private val splashRepository: SplashRepository) :
    BasePresenter<SplashView>() {

    fun checkSession() {
        splashRepository.checkUser {
            view()?.resultSession(it.success)
        }
    }

}