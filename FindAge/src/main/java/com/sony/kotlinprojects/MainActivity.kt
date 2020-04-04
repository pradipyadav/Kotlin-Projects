package com.sony.kotlinprojects

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFindMyAge.setOnClickListener {
            try {


            val edtAge = edtAge.text.toString().toInt()
            val currenYear = Calendar.getInstance().get(Calendar.YEAR)

            Log.e("TAGG",currenYear.toString())

            if(edtAge.toInt()==0){
                txtMyAge.text = "Invalid age"
                return@setOnClickListener
            }else if (edtAge.toInt()>currenYear){
                txtMyAge.text = "Invalid age Entered"
                return@setOnClickListener
            }else{
                val myAge = currenYear - edtAge.toInt()

                txtMyAge.text = "Your Age is $myAge Years"


        }
        }catch (e:Exception){
            Log.e("eksjndejdns",e.message)
        }

    }
    }
}
