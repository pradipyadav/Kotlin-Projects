package com.sony.sqlitedb

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_customer_update.view.*
import kotlinx.android.synthetic.main.lo_customers.view.*

class CustomerAdapter(mCtx: Context, val customers: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {


    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtCustomerName = itemView.txtCustomerName
        var txtMaxCredit = itemView.txtMaxCredit

        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_customers, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: CustomerAdapter.ViewHolder, position: Int) {

        val customer: Customer = customers[position]
        holder.txtCustomerName.text = customer.customerName
        holder.txtMaxCredit.text = customer.maxCredit.toString()

        holder.btnDelete.setOnClickListener {
            val customerName = customer.customerName
            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Warning")
                .setMessage("Are You Sure to Delete : $customerName ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    if (MainActivity.dbHandler.deleteCustomer(customer.customerId)) {
                        customers.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, customers.size)
                        Toast.makeText(mCtx, "Customer $customerName Deleted", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(mCtx, "Error Deleting", Toast.LENGTH_LONG).show()

                    }
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> })
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show()
        }

        holder.btnUpdate.setOnClickListener {
            val inflater = LayoutInflater.from(mCtx)
            val view = inflater.inflate(R.layout.lo_customer_update, null)

            val txtCustomerName: TextView = view.findViewById(R.id.editUpCustomerName)
            val txtMaxCredit: TextView = view.findViewById(R.id.editUpMaxCredit)

            txtCustomerName.text = customer.customerName
            txtMaxCredit.text = customer.maxCredit.toString()

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Update Customer Info")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
                    val isUpdate: Boolean = MainActivity.dbHandler.updateCustomer(
                        customer.customerId.toString(),
                        view.editUpCustomerName.text.toString(),
                        view.editUpMaxCredit.text.toString()
                    )
                    if (isUpdate == true) {
                        customers[position].customerName = view.editUpCustomerName.text.toString()
                        customers[position].maxCredit =
                            view.editUpMaxCredit.text.toString().toDouble()
                        notifyDataSetChanged()
                        Toast.makeText(mCtx, "Updated Successfully", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(mCtx, "Error Updating", Toast.LENGTH_LONG).show()
                    }

                }).setNegativeButton(
                    "Cance", DialogInterface.OnClickListener { dialogInterface, i ->

                    }
                )
            val alert = builder.create()
            alert.show()
        }
    }

}