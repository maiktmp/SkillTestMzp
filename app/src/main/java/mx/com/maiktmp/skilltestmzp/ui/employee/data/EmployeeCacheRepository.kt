package mx.com.maiktmp.skilltestmzp.ui.employee.data

import android.content.Context
import com.google.gson.Gson
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.utils.Extensions.convertToList
import mx.com.maiktmp.skilltestmzp.utils.PreferenceHelper
import mx.com.maiktmp.web.api.ApiConnection

class EmployeeCacheRepository(private val context: Context?) {

    private val gson = Gson()

    fun getEmployees(cbResult: (GenericResponse<List<Employee>?>) -> Unit) {
        val employees = PreferenceHelper.retrievePreference(PreferenceHelper.KEY.EMPLOYEES_CACHE)
        if (employees == null) {
            cbResult(GenericResponse(message = context?.getString(R.string.error_employees_empty)))
            return
        }
        val res = gson.fromJson(employees, Array<Employee>::class.java)
        cbResult(GenericResponse(success=true,data = res.toList()))
    }

    fun storeCache(list: List<Employee>) {
        val cache = gson.toJsonTree(list).toString()
        PreferenceHelper.storeString(PreferenceHelper.KEY.EMPLOYEES_CACHE, cache)
    }

}