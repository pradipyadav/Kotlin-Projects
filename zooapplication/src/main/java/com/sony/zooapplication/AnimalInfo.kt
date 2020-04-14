package com.sony.zooapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfo: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val bundle: Bundle? =intent.extras

        val name= bundle!!.getString("name")
        val desc= bundle!!.getString("desc")
        val img= bundle!!.getInt("img")

        ivAnimaImage.setImageResource(img)
        tvName.text=name
        tvDes.text=desc
    }
}