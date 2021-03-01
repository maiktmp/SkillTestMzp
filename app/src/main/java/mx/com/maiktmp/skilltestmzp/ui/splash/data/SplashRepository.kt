package mx.com.maiktmp.skilltestmzp.ui.splash.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.maiktmp.dao.UserDao
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse

class SplashRepository(private val userDao: UserDao) {

    fun checkUser(cbResult: (GenericResponse<String>) -> Unit): Disposable {
        return userDao.findActive()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cbResult(GenericResponse(success = true, data = "")) },
                { cbResult(GenericResponse(data = "")) },
                { cbResult(GenericResponse(data = "")) })
    }

}