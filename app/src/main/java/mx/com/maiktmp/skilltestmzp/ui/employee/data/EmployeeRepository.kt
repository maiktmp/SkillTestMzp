package mx.com.maiktmp.skilltestmzp.ui.employee.data

import android.content.Context
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.utils.Extensions.convertToList
import mx.com.maiktmp.web.api.ApiConnection

class EmployeeRepository(private val api: ApiConnection, private val context: Context?) {

    fun getEmployees(cbResult: (GenericResponse<List<Employee>?>) -> Unit) {
        api.getEmployees { status, result ->
            if (!status) {
                cbResult(GenericResponse(message = context?.getString(R.string.error_login)))
                return@getEmployees
            }
            cbResult(GenericResponse(status, data = result!!.employees.convertToList(Employee::class.java)))
        }
    }


}