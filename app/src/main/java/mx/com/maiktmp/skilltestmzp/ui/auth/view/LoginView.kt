package mx.com.maiktmp.skilltestmzp.ui.auth.view

import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.ui.models.User

interface LoginView {

    fun handleLoginSuccess()

    fun handleLoginError(gr: GenericResponse<User?>)

    fun showProgress()

    fun hideProgress()

}