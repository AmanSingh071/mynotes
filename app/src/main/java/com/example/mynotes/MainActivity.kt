package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.databinding.ItemNotesBinding
import com.google.firebase.database.*

class mainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var MusicAdapter: notesdAdapter
    lateinit var MusicListActivity:ArrayList<notes>








    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MusicListActivity= arrayListOf<notes>()
        binding.recy.layoutManager = LinearLayoutManager(this)

        MusicAdapter=notesdAdapter(this, MusicListActivity )
        binding.recy.adapter=MusicAdapter

        getuserdata()
    }
    private fun getuserdata() {
        dbref= FirebaseDatabase.getInstance().getReference("Songs")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                MusicListActivity.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        userSnapshot.getValue(notes::class.java)
                            ?.let { MusicListActivity.add(it) }

                    }
                }


                binding.recy.adapter= notesdAdapter(this@mainActivity, MusicListActivity)


                /*postDao = PostDao()
        val postsCollections = postDao.postCollections
        val query = postsCollections.orderBy("songname", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Song>().setQuery(query, Song::class.java).build()

        uploadAdapter = PostAdapter(recyclerViewOptions, this)


        binding.recyclerviewid.layoutManager = LinearLayoutManager(this)*/

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



    }

}