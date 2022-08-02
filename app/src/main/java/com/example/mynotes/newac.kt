package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.mynotes.databinding.ActivityNewacBinding
import com.google.firebase.database.*
import com.google.firebase.firestore.FieldValue

class newac : AppCompatActivity() {
    lateinit var listmy:ArrayList<notes>
    private lateinit var binding: ActivityNewacBinding
    lateinit var ref1: DatabaseReference
    var noteposition:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewacBinding.inflate(layoutInflater)
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
                listmy = ArrayList()
                listmy.addAll(mainActivity.myListActivity)
                binding.initialtxt.text= listmy[noteposition].inctxt.toString()
               var ii= listmy[noteposition].inctxt

                binding.fintxt.text= listmy[noteposition].dectxt.toString()
                var gg= listmy[noteposition].dectxt
                var audioid=listmy[noteposition].id
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