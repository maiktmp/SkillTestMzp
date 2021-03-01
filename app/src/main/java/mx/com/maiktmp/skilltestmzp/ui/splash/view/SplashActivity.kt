package mx.com.maiktmp.skilltestmzp.ui.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import mx.com.maiktmp.DBSkillTestMzp
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.databinding.ActivitySplashBinding
import mx.com.maiktmp.skilltestmzp.ui.auth.view.LoginActivity
import mx.com.maiktmp.skilltestmzp.ui.employee.view.EmployeesActivity
import mx.com.maiktmp.skilltestmzp.ui.splash.data.SplashRepository
import mx.com.maiktmp.skilltestmzp.ui.splash.presenter.SplashPresenter
import mx.com.maiktmp.skilltestmzp.ui.splash.view.interfaces.SplashView

class SplashActivity : AppCompatActivity(), SplashView {

    private val vBind: ActivitySplashBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    private val presenter: SplashPresenter by lazy {
        SplashPresenter(SplashRepository(DBSkillTestMzp.db.userDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this, lifecycle)
        vBind.lblScreen
        presenter.checkSession()
    }

    override fun resultSession(exist: Boolean) {
        if (exist) {
            startActivity(Intent(this, EmployeesActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

}