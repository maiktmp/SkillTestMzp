package mx.com.maiktmp.skilltestmzp.ui.employee.presenter

import mx.com.maiktmp.skilltestmzp.base.BasePresenter
import mx.com.maiktmp.skilltestmzp.ui.employee.data.UserRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces.EmployeesView
import mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces.UserView

class UserPresenter(private val userRepository: UserRepository) : BasePresenter<UserView>() {

    fun logout() {
        userRepository.logout {
            if (it.success) {
                view()?.logout()
            } else {
                view()?.handleUnsuccessful(it.message!!)
            }
        }.autoClear()
    }

}
