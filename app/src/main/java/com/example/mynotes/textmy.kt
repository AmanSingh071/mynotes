package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.databinding.ItemNotesBinding
import com.example.mynotes.databinding.TextmBinding

import com.google.firebase.database.*

class textmy : AppCompatActivity() {
    lateinit var binding: TextmBinding
     lateinit var binding2:ItemNotesBinding
     lateinit var binding3: ActivityMainBinding
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
           senddata()

        }

        binding.btn.setOnClickListener {
            val intent= Intent(this, mainActivity::class.java)
            startActivity(intent)
        }




    }

    private fun senddata() {


        var maxid: Int? =binding3.recy.adapter?.itemCount
        var txtenter = binding.texentert.text.toString()
        var ref11: DatabaseReference = FirebaseDatabase.getInstance().getReference("Songs")
        ref11.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    maxid=(snapshot.childrenCount.toInt())

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val music= maxid?.let { notes(txtenter, it) }

        val songobj: notes? = music
        ref11.child(( maxid).toString()).setValue(songobj).addOnCompleteListener {
            if(it.isSuccessful()){
                Toast.makeText(this,"Song Uploaded", Toast.LENGTH_SHORT).show()


            }
        }.addOnFailureListener {
            Toast.makeText(this,it.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    }

