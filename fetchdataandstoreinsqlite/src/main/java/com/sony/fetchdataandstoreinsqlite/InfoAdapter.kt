package com.sony.fetchdataandstoreinsqlite

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
import kotlinx.android.synthetic.main.lo_info.view.*

class InfoAdapter(mCtx: Context, val infos: ArrayList<InfoData>) :
    RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    val mCtx = mCtx


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameInfo = itemView.name
        var userNameInfo = itemView.userName

        val emailInfo = itemView.email

        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_info, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return infos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info: InfoData = infos[position]
        holder.nameInfo.text = info.name
        holder.userNameInfo.text = info.username
        holder.emailInfo.text = info.email

        holder.btnDelete.setOnClickListener {
            val customerName = info.name
            var alertDialog = AlertDialog.Builder(mCtx)
                .setTitle("Warning")
                .setMessage("Are You Sure to Delete : $customerName ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    if (MainActivity.dbHandler.deleteCustomer(info.id)) {
                        infos.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, infos.size)
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
            val editUpUN: TextView = view.findViewById(R.id.editUpUN)
            val edtUpEmailId: TextView = view.findViewById(R.id.edtUpEmailId)

            txtCustomerName.text = info.name
            editUpUN.text = info.username
            edtUpEmailId.text = info.email

            val builder = AlertDialog.Builder(mCtx)
                .setTitle("Update Customer Info")
                .setView(view)
                .setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
                    val isUpdate: Boolean = MainActivity.dbHandler.updateCustomer(
                        info.id.toString(),
                        view.editUpCustomerName.text.toString(),
                        view.editUpUN.text.toString(),
                        view.edtUpEmailId.text.toString()
                    )
                    if (isUpdate == true) {
                        infos[position].name = view.editUpCustomerName.text.toString()
                        infos[position].username = view.editUpUN.text.toString()
                        infos[position].email = view.edtUpEmailId.text.toString()
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