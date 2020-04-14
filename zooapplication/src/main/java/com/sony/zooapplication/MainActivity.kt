package com.sony.zooapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter: AnimalsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load  animals
        listOfAnimals.add(
            Animal("Baboon", "Baboon live in  big place with tree", R.drawable.baboon, true)
        )
        listOfAnimals.add(
            Animal("Bulldog", "Bulldog live in  big place with tree", R.drawable.bulldog, true)
        )
        listOfAnimals.add(
            Animal("Panda", "Panda live in  big place with tree", R.drawable.panda, false)
        )
        listOfAnimals.add(
            Animal("Swallow", "Swallow live in  big place with tree", R.drawable.swallow_bird, true)
        )
        listOfAnimals.add(
            Animal("Tiger", "Tiger live in  big place with tree", R.drawable.white_tiger, false)
        )
        listOfAnimals.add(
            Animal("Zebra", "Zebra live in  big place with tree", R.drawable.zebra, false)
        )

        adapter = AnimalsAdapter(this, listOfAnimals)
        tvListAnimal.adapter = adapter


    }

    fun delete(index: Int) {
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index: Int) {
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter : BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null

        constructor(context: Context, listOfAnimals: ArrayList<Animal>) : super() {
            this.listOfAnimals = listOfAnimals
            this.context = context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]

            if (animal.killer == true) {
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.desc!!
                myView.ivAnimalImage.setImageResource(animal.img!!)

                myView.ivAnimalImage.setOnClickListener {

//                    delete(p0)
//                    add(p0)

                       val intent=Intent(context,AnimalInfo::class.java)
                       intent.putExtra("name",animal.name!!)
                       intent.putExtra("desc",animal.desc!!)
                       intent.putExtra("img",animal.img!!)
                       context!!.startActivity(intent)
                }

                return myView

            } else {


                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.desc!!
                myView.ivAnimalImage.setImageResource(animal.img!!)

                myView.ivAnimalImage.setOnClickListener {

//                    delete(p0)
//                    add(p0)

                      val intent= Intent(context,AnimalInfo::class.java)
                      intent.putExtra("name",animal.name!!)
                      intent.putExtra("desc",animal.desc!!)
                      intent.putExtra("img",animal.img!!)
                      context!!.startActivity(intent)
                }
                return myView


            }
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfAnimals.size
        }
    }
}



