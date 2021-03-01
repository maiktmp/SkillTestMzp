package mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import mx.com.maiktmp.skilltestmzp.databinding.FragmentEmployeesBinding
import mx.com.maiktmp.skilltestmzp.ui.employee.data.EmployeeCacheRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.data.EmployeeRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.presenter.EmployeePresenter
import mx.com.maiktmp.skilltestmzp.ui.employee.view.EmployeesActivity
import mx.com.maiktmp.skilltestmzp.ui.employee.view.adapter.EmployeeAdapter
import mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces.EmployeesView
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.utils.Extensions.displayToast
import mx.com.maiktmp.skilltestmzp.utils.Extensions.hideView
import mx.com.maiktmp.skilltestmzp.utils.Extensions.showView
import mx.com.maiktmp.web.api.ApiConnection

class EmployeesFragment : Fragment(), EmployeesView {

    private val presenter: EmployeePresenter by lazy {
        EmployeePresenter(
            context,
            EmployeeRepository(ApiConnection, context),
            EmployeeCacheRepository(context)
        )
    }

    lateinit var vBind: FragmentEmployeesBinding
    lateinit var employeesListener: EmployeesListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBind = FragmentEmployeesBinding.inflate(inflater, container, false)

        presenter.attachView(this, lifecycle)
        presenter.getEmployees()
        return vBind.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        employeesListener = context as EmployeesActivity
    }

    private fun setupRecyclerView() {
        vBind.rvEmployees.apply {
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupAdapter(employees: List<Employee>) {
        val adapter = EmployeeAdapter(employees)
        adapter.onEmployeeClick = { employeesListener.onEmployeeClick(it) }
        vBind.rvEmployees.adapter = adapter
    }


    override fun listEmployees(employees: List<Employee>) {
        if (employees.isEmpty()) {
            vBind.rvEmployees.hideView()
            vBind.lblEmpty.showView()
            return
        }
        setupRecyclerView()
        setupAdapter(employees)
    }

    override fun handleUnsuccessful(message: String?) {
        message?.let {
            context?.displayToast(message, Toast.LENGTH_SHORT)
        }
    }

    override fun showProgress() {
        vBind.pbLoading.showView()
    }

    override fun hideProgress() {
        vBind.pbLoading.hideView()
    }

    interface EmployeesListener {
        fun onEmployeeClick(employee: Employee)
    }
}