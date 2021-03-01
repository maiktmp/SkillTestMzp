package mx.com.maiktmp.skilltestmzp.ui.auth.presenter

import mx.com.maiktmp.skilltestmzp.base.BasePresenter
import mx.com.maiktmp.skilltestmzp.ui.auth.data.LoginRepository
import mx.com.maiktmp.skilltestmzp.ui.auth.view.intefaces.LoginView
import mx.com.maiktmp.skilltestmzp.ui.models.User

class LoginPresenter(private val repository: LoginRepository) : BasePresenter<LoginView>() {

    fun attemptLogin(user: User) {
        view()?.showProgress()
        repository.attemptLogin(user) {
            repository.storeUser(user) {
                view()?.hideProgress()
                if (it.success) {
                    view()?.handleLoginSuccess()
                } else {
                    view()?.handleLoginError(it)
                }
            }.autoClear()
        }
    }

}