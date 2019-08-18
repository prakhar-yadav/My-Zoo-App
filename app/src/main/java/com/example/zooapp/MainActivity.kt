package com.example.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_animal_info.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_killer_ticket.view.*
import kotlinx.android.synthetic.main.animal_ticket.view.*
import kotlinx.android.synthetic.main.animal_ticket.view.ivName
import kotlinx.android.synthetic.main.animal_ticket.view.tvDes
import kotlinx.android.synthetic.main.animal_ticket.view.tvName
import java.nio.file.Files.delete

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOfAnimals.add(Animal("Baboon","Baboon is a wild animal and lives in wild",R.drawable.baboon,true))
        listOfAnimals.add(Animal("Bulldog","Bulldog is a pet dog breed and it is aggresive in nature.",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Panda eats banboo",R.drawable.panda,true))
        listOfAnimals.add(Animal("Swallow Bird","Swallow Bird is a big bird",R.drawable.swallow_bird,false))
        listOfAnimals.add(Animal("White Tiger","White tiger is rare in nature",R.drawable.white_tiger,true))
        listOfAnimals.add(Animal("Zebra","Zebra is black and white in color",R.drawable.zebra,false))
        adapter = AnimalAdapter(this,listOfAnimals)
        tvListAnimal.adapter=adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index:Int){
        listOfAnimals.add(index,listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalAdapter:BaseAdapter{

        var listOfAnimals : ArrayList<Animal>
        var context:Context?=null
        constructor(context:Context, listOfAnimals: ArrayList<Animal> ):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            val animal = listOfAnimals[p0]
            if(animal.isKiller==true){

                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivName.setImageResource(animal.image!!)
                myView.killerTicket.setOnClickListener{
                    /*
                    TO ADD ELEMENT IN LIST
                    add(p0)
                    */
                    /*
                    TO DELETE ANY ANIMAL FROM LIST
                    delete(p0);
                    */

                    //EVENT TO GO TO NEXT ACTIVITY

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)

                }
                return myView
            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivName.setImageResource(animal.image!!)
                myView.ticket.setOnClickListener {

                    /*
                    TO ADD ELEMENT IN LIST
                    add(p0)
                    */
                    /*
                    TO DELETE ANY ANIMAL FROM LIST
                    delete(p0);
                    */

                   // EVENT TO GO TO NEXT ACTIVITY

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
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
