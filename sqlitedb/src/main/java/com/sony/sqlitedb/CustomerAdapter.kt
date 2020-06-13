package com.sony.sqlitedb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        val customer:Customer=customers[position]
        holder.txtCustomerName.text=customer.customerName
        holder.txtMaxCredit.text=customer.maxCredit.toString()
    }

}