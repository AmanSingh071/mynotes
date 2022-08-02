package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.databinding.TextmBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.google.firebase.database.*

class mainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var binding2: TextmBinding
    private lateinit var noteAdapter: notesdAdapter

 companion object{
     lateinit var myListActivity:ArrayList<notes>
 }














    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        binding2 = TextmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myListActivity= arrayListOf<notes>()
        binding.recy.layoutManager = LinearLayoutManager(this)

        noteAdapter=notesdAdapter(this,myListActivity )
        binding.recy.adapter=noteAdapter
        binding.addnotebtn.setOnClickListener{
            val intent= Intent(this, textmy::class.java)
            startActivity(intent)

        }


        getuserdata()
    }


    private fun getuserdata() {
        dbref= FirebaseDatabase.getInstance().getReference("Notes")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                myListActivity.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        userSnapshot.getValue(notes::class.java)
                            ?.let { myListActivity.add(it) }

                    }
                }


                binding.recy.adapter= notesdAdapter(this@mainActivity, myListActivity)




            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@mainActivity,"error", Toast.LENGTH_SHORT).show()
            }

        })



    }

}