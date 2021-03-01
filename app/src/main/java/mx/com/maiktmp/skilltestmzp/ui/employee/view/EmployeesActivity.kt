package mx.com.maiktmp.skilltestmzp.ui.employee.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import mx.com.maiktmp.DBSkillTestMzp
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.databinding.ActivityEmployeesBinding
import mx.com.maiktmp.skilltestmzp.ui.auth.view.LoginActivity
import mx.com.maiktmp.skilltestmzp.ui.employee.data.UserRepository
import mx.com.maiktmp.skilltestmzp.ui.employee.presenter.UserPresenter
import mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments.EmployeeDetailsFragment
import mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments.EmployeesFragment
import mx.com.maiktmp.skilltestmzp.ui.employee.view.interfaces.UserView
import mx.com.maiktmp.skilltestmzp.ui.models.Employee
import mx.com.maiktmp.skilltestmzp.utils.Codes
import mx.com.maiktmp.skilltestmzp.utils.Extensions.displayToast


class EmployeesActivity : AppCompatActivity(), EmployeesFragment.EmployeesListener, UserView {

    private val vBind: ActivityEmployeesBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_employees)
    }

    private val presenter: UserPresenter by lazy {
        UserPresenter(UserRepository(DBSkillTestMzp.db.userDao(), this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this, lifecycle)
        setupToolbar()
        setupMainFragment()
        setupFragmentTitles()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(vBind.iToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun updateTitle(title: String) {
        vBind.iToolbar.tvTitle.text = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.log_out -> {
                displayLogoutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupFragmentTitles() {
        supportFragmentManager.addOnBackStackChangedListener {
            when (supportFragmentManager.fragments.last().tag) {
                Codes.FRAGMENT_EMPLOYEES -> {
                    updateTitle(getString(R.string.employees))
                    enableBackButton(false)
                }
                Codes.FRAGMENT_EMPLOYEE_DETAILS -> {
                    updateTitle(getString(R.string.employee_detail))
                    enableBackButton(true)
                }
            }
        }
    }

    private fun enableBackButton(isEnabled: Boolean = true) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isEnabled)
    }

    private fun setupMainFragment() {
        if (supportFragmentManager.findFragmentByTag(Codes.FRAGMENT_EMPLOYEES) != null) return
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fl_root, EmployeesFragment(), Codes.FRAGMENT_EMPLOYEES)
            .commit()
        updateTitle(getString(R.string.employees))
        enableBackButton(false)
    }

    private fun displayLogoutDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.log_out))
            .setMessage(getString(R.string.log_out_ad))
            .setNegativeButton(getString(R.string.ok)) { _, _ ->
                presenter.logout()
            }
            .setPositiveButton(getString(R.string.cancel)) { _, _ ->
            }
            .show()
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

    override fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    override fun handleUnsuccessful(message: String) {
        displayToast(message, Toast.LENGTH_SHORT)
    }
}