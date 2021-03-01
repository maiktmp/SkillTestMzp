package mx.com.maiktmp.skilltestmzp.ui.employee.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.maiktmp.skilltestmzp.R
import mx.com.maiktmp.skilltestmzp.databinding.ItemEmployeeBinding
import mx.com.maiktmp.skilltestmzp.ui.models.Employee

class EmployeeAdapter(private val items: List<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    var onEmployeeClick: (employee: Employee) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        )

    override fun getItemCount(): Int = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = items[position]
        val vBind = holder.vBind
        vBind.employee = employee

        Glide.with(vBind.ivProfile)
            .load(employee.image)
            .into(vBind.ivProfile)

        vBind.itemRoot.setOnClickListener { onEmployeeClick(employee) }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vBind = ItemEmployeeBinding.bind(itemView)
    }
}