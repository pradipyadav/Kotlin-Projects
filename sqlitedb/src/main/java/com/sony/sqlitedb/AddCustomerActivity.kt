package com.sony.sqlitedb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_customer.*

class AddCustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer)

        btnAdd.setOnClickListener {

            if (edtCustomerName.text.isEmpty()) {
                Toast.makeText(this, "Enter Customer Name", Toast.LENGTH_LONG).show()
                edtCustomerName.requestFocus()
            } else {
                val customer = Customer()
                customer.customerName = edtCustomerName.text.toString()
                if (edtMaxCredit.text.isEmpty())
                    customer.maxCredit = 0.0 else
                    customer.maxCredit = edtMaxCredit.text.toString().toDouble()
                MainActivity.dbHandler.addCustomer(this, customer)
                clearEdits()
                edtCustomerName.requestFocus()
            }
        }

        btnCancel.setOnClickListener {
            clearEdits()
            finish()
        }
    }

    private fun clearEdits() {

        edtCustomerName.text.clear()
        edtMaxCredit.text.clear()
    }
}
