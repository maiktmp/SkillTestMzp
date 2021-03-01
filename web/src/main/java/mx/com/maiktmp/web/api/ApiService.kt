package mx.com.maiktmp.web.api


import mx.com.maiktmp.web.ApiServer
import mx.com.maiktmp.web.api.models.EmployeeAPI
import mx.com.maiktmp.web.api.models.Request.EmployeeRequest
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("${ApiServer.name}/")
    fun getEmployees(): Call<EmployeeRequest>

}