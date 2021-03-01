package mx.com.maiktmp.skilltestmzp.ui.auth.view

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import mx.com.maiktmp.DBSkillTestMzp

import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.databinding.ActivityLoginBinding
import mx.com.maiktmp.skilltestmzp.ui.auth.data.LoginRepository
import mx.com.maiktmp.skilltestmzp.ui.auth.presenter.LoginPresenter
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.ui.models.User
import mx.com.maiktmp.skilltestmzp.utils.Extensions.displayToast
import mx.com.maiktmp.skilltestmzp.utils.Extensions.required
import mx.com.maiktmp.skilltestmzp.utils.Extensions.validateMail

class LoginActivity : AppCompatActivity(), LoginView {

    private val vBind: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val presenter: LoginPresenter by lazy {
        LoginPresenter(LoginRepository(DBSkillTestMzp.db.userDao(), this))
    }

    private var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBind.user = user
        presenter.attachView(this, lifecycle)
        setupLoginBtn()
    }

    private fun setupLoginBtn() {
        vBind.btnLogin.setOnClickListener {
            if (isFormValid()) {
                presenter.attemptLogin(user)
            }
        }
    }

    private fun isFormValid(): Boolean {
        var isValid = true
        isValid = isValid && vBind.tilEmail.validateMail()
        isValid = isValid && vBind.tilPassword.required()
        return isValid
    }

    override fun handleLoginSuccess() {
        displayToast("Welcome", Toast.LENGTH_SHORT)
    }

    override fun handleLoginError(gr: GenericResponse<User?>) {
        displayToast(gr.message, Toast.LENGTH_SHORT)
    }

    override fun showProgress() {
        vBind.btnLogin.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }
    }

    override fun hideProgress() {
        vBind.btnLogin.hideProgress(R.string.log_in)
    }
}