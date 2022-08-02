package com.example.mynotes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.databinding.ItemNotesBinding
import com.example.mynotes.databinding.TextmBinding
import com.example.mynotes.notesdAdapter.Companion.size
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.google.firebase.database.*

class textmy : AppCompatActivity() {
    lateinit var binding: TextmBinding
     lateinit var binding2:ItemNotesBinding
     lateinit var binding3: ActivityMainBinding
     var maxid=0


       var main:mainActivity?=null
    lateinit var MusicListActivity:ArrayList<notes>
    private lateinit var MusicAdapter: notesdAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = TextmBinding.inflate(layoutInflater)
        binding2 =  ItemNotesBinding.inflate(layoutInflater)
        binding3 =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.senddatabtn.setOnClickListener {
          uploadsongtostorage()

        }
        binding.btn.setOnClickListener{
            val intent= Intent(this, mainActivity::class.java)
            startActivity(intent)

        }



    }

    private fun uploadsongtostorage() {

        var txtenter = binding.texentert.text.toString()
        var inienter = binding.entinc.text.toString().toInt()
        var finenter = binding.entdec.text.toString().toInt()






                        var ref11: DatabaseReference =FirebaseDatabase.getInstance().getReference("Notes")
                        ref11.addValueEventListener(object :ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if(snapshot.exists()){
                                    size=0

                                    size=(snapshot.childrenCount.toInt())

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@textmy,"error",Toast.LENGTH_SHORT).show()
                            }

                        })

                        /* if (ref11 != null) {*/
                        val music= notes(txtenter,size,inienter,finenter)

                        val songobj: notes= music
                        ref11.child((size).toString()).setValue(songobj).addOnCompleteListener {
                            if(it.isSuccessful()){
                                Toast.makeText(this ,"Notes Added",Toast.LENGTH_SHORT).show()


                            }
                        }.addOnFailureListener {
                            Toast.makeText(this ,it.message.toString(),Toast.LENGTH_SHORT).show()
                        }



                        Toast.makeText(this,"Notes Added",Toast.LENGTH_SHORT).show()

                    }














    }


