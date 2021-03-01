package mx.com.maiktmp.skilltestmzp.ui.employee.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.databinding.ActivityEmployeesBinding
import mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments.EmployeeDetailsFragment
import mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments.EmployeesFragment
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.utils.Codes


class EmployeesActivity : AppCompatActivity(), EmployeesFragment.EmployeesListener {

    private val vBind: ActivityEmployeesBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_employees)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind.flRoot
        setupMainFragment()
    }

    private fun setupMainFragment() {
        if (supportFragmentManager.findFragmentByTag(Codes.FRAGMENT_EMPLOYEES) != null) return
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_root, EmployeesFragment(), Codes.FRAGMENT_EMPLOYEES)
            .commit()
    }

    private fun displayEmployeeDetails(employee: Employee) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.fade_out
            )
            .replace(
                R.id.fl_root,
                EmployeeDetailsFragment.createInstance(employee),
                Codes.FRAGMENT_EMPLOYEE_DETAILS
            )
            .addToBackStack(Codes.FRAGMENT_EMPLOYEE_DETAILS)
            .commit()
    }

    /**
     * @see EmployeesFragment.EmployeesListener
     */
    override fun onEmployeeClick(employee: Employee) {
        displayEmployeeDetails(employee)
    }
}