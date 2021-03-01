package mx.com.maiktmp.skilltestmzp.ui.employee.presenter

import android.content.Context
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.base.BasePresenter
import mx.com.maiktmp.skilltestmzp.ui.employee.data.EmployeeCacheRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.data.EmployeeRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.data.UserRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces.EmployeesView
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.utils.NetworkUtil

class EmployeePresenter(
    private val context: Context?,
    private val apiRepository: EmployeeRepository,
    private val cacheRepository: EmployeeCacheRepository,
) :BasePresenter<EmployeesView>() {

    fun getEmployees() {
        view()?.showProgress()
        if (NetworkUtil.isNetworkAvailable(context)) {
            loadApiEmployees()
        } else {
            view()?.handleUnsuccessful(context?.getString(R.string.error_network))
            loadCacheEmployees()
        }
    }


    private fun loadApiEmployees() {
        apiRepository.getEmployees {
            processResponse(it)
            it.data?.let { employees ->
                cacheRepository.storeCache(employees)
            }
        }
    }

    private fun loadCacheEmployees() {
        cacheRepository.getEmployees {
            processResponse(it)
        }
    }

    private fun processResponse(gr: GenericResponse<List<Employee>?>) {
        if (gr.success) {
            view()?.listEmployees(gr.data!!)
        } else {
            view()?.handleUnsuccessful(gr.message!!)
        }
        view()?.hideProgress()
    }
}