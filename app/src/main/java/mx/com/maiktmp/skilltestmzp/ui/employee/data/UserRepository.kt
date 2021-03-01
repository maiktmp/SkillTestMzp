package mx.com.maiktmp.skilltestmzp.ui.employee.data

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.maiktmp.dao.UserDao
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse

class UserRepository(private val userDao: UserDao, private val context: Context) {

    fun logout(cbResult: (GenericResponse<String?>) -> Unit): Disposable {
        return userDao
            .deleteUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cbResult(GenericResponse(success = true, data = "")) },
                { cbResult(GenericResponse(message = context.getString(R.string.error_logout), data = "")) })
    }
}