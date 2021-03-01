package mx.com.maiktmp.skilltestmzp.ui.auth.data

import android.annotation.SuppressLint
import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mx.com.maiktmp.dao.UserDao
import mx.com.maiktmp.entities.UserDB
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.ui.models.GenericResponse
import mx.com.maiktmp.skilltestmzp.ui.models.User
import java.util.concurrent.TimeUnit

class LoginRepository(private val userDao: UserDao, private val context: Context) {

    @SuppressLint("CheckResult")
    fun attemptLogin(user: User, cbResult: (User) -> Unit) {
        Observable.create<String> { emitter ->
            emitter.onNext("next")
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .delay(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cbResult(user) }
    }


    fun storeUser(user: User, cb: (GenericResponse<User?>) -> Unit): Disposable {
        return userDao.upsert(UserDB(email = user.email))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cb(GenericResponse(true, data = user) )},
                { _ -> cb(GenericResponse(false, context.getString(R.string.error_login), null)) }
            )
    }

}