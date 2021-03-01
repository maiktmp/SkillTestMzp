package mx.com.maiktmp.skilltestmzp.ui.employee.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import mx.com.maiktmp.skilltestmzp.databinding.FragmentEmployeeDetailsBinding
import mx.com.maiktmp.skilltestmzp.ui.models.Employee

class EmployeeDetailsFragment : Fragment() {

    companion object {
        private const val EMPLOYEE = "EMPLOYEE"

        fun createInstance(employee: Employee): EmployeeDetailsFragment {
            val args = Bundle().apply {
                putString(EMPLOYEE, Gson().toJson(employee).toString())
            }
            return EmployeeDetailsFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var vBind: FragmentEmployeeDetailsBinding
    private lateinit var employee: Employee

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBind = FragmentEmployeeDetailsBinding.inflate(inflater, container, false)
        employee = Gson().fromJson(arguments?.getString(EMPLOYEE), Employee::class.java)
        displayEmployInfo()
        return vBind.root
    }


    private fun displayEmployInfo() {
        vBind.employee = employee
        Glide.with(vBind.ivProfile)
            .load(employee.image)
            .into(vBind.ivProfile)
    }
}