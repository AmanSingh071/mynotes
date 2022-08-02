package com.example.mynotes

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mynotes.databinding.ActivityMainBinding

import com.example.mynotes.databinding.ActivityNewacBinding
import com.example.mynotes.databinding.ItemNotesBinding
import com.google.firebase.database.*
import com.google.firebase.firestore.FieldValue

class newac : AppCompatActivity() {
    lateinit var listmusic:ArrayList<notes>
    private lateinit var binding: ActivityNewacBinding
    private lateinit var binding2:ItemNotesBinding
    lateinit var ref1: DatabaseReference
    var noteposition:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewacBinding.inflate(layoutInflater)
        binding2 =  ItemNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rtnmain.setOnClickListener{
            val intent= Intent(this, mainActivity::class.java)
            startActivity(intent)
            finish()
        }
        initialiszelayout()
    }
    private fun  initialiszelayout() {
        noteposition = intent.getIntExtra("index", 0)
        when (intent.getStringExtra("class")) {

            "notesadapter" -> {
                listmusic = ArrayList()
                listmusic.addAll(mainActivity.myActivity)
                binding.initialtxt.text= listmusic[ noteposition].inctxt.toString()
               var ii= listmusic[ noteposition].inctxt

                binding.fintxt.text= listmusic[ noteposition].dectxt.toString()
                var gg= listmusic[ noteposition].dectxt
                var audioid=listmusic[ noteposition].id
                ref1= FirebaseDatabase.getInstance().getReference("Notes")
               binding.increinitialbtn.setOnClickListener {
                   ii= ii?.plus(1)



                                    ref1.child(audioid.toString()).child("inctxt").setValue(ii)

                   binding.initialtxt.text= ii.toString()



                }
                  binding.decreinibtn.setOnClickListener {
                    ii= ii?.minus(1)



                    ref1.child(audioid.toString()).child("inctxt").setValue(ii)

                      binding.initialtxt.text= ii.toString()



                }

                binding.incrfinbtn.setOnClickListener {
                    gg= gg?.plus(1)



                    ref1.child(audioid.toString()).child("dectxt").setValue(gg)

                    binding.fintxt.text= gg.toString()



                }
                binding.decfinbtn.setOnClickListener {
                    gg= gg?.minus(1)



                    ref1.child(audioid.toString()).child("dectxt").setValue(gg)

                    binding.fintxt.text= gg.toString()



                }




                //this will display name and song image


            }



        }
    }
}