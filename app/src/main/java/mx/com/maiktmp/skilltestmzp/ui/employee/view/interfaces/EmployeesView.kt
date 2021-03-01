package mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces

import mx.com.maiktmp.skilltestmzp.ui.models.Employee

interface EmployeesView {

    fun listEmployees(employees: List<Employee>)

    fun handleUnsuccessful(message: String?)

    fun showProgress()

    fun hideProgress()


}