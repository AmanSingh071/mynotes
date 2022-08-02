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
    private lateinit var MusicAdapter: notesdAdapter

 companion object{
     lateinit var myActivity:ArrayList<notes>
 }














    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        binding2 = TextmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myActivity= arrayListOf<notes>()
        binding.recy.layoutManager = LinearLayoutManager(this)

        MusicAdapter=notesdAdapter(this, myActivity )
        binding.recy.adapter=MusicAdapter
        binding.addnotebtn.setOnClickListener{
            val intent= Intent(this, textmy::class.java)
            startActivity(intent)

        }






        getuserdata()
    }

  /*  private fun change() {

            songposition = intent.getIntExtra("index", 0)
            when (intent.getStringExtra("class")) {

                "uploadAdapter" -> {


                    binding.inctxt.text= MusicListActivity[songposition].inctxt
                    binding.dectxt.text= MusicListActivity[songposition].dectxt


                    //this will display name and song image


                }



            }

    }*/
  private fun uploadsongtostorage() {
      val firebaseuser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

      val rootref: DatabaseReference = FirebaseDatabase.getInstance().getReference()

      var likes=0
      /*  with(templist[position]) {
            postId = id
        }*/
      var txtenter = binding2.texentert.text.toString()
      var inienter = binding2.entinc.text.toString().toInt()
      var finenter = binding2.entdec.text.toString().toInt()
      var maxid:Int=binding.recy.adapter!!.itemCount



      var currtime=System.currentTimeMillis()


      /*  var id: String? = "",
        var songname: String? = "",
        var duration: Long= 0,
        var album: String? = "",
        var path: String? = "",
        val artist:String? = "",
        var songurl: String="",
        var imageurl: String? = ""*/


      var ref11: DatabaseReference =FirebaseDatabase.getInstance().getReference("Songs")
      ref11.addValueEventListener(object :ValueEventListener{
          override fun onDataChange(snapshot: DataSnapshot) {
              if(snapshot.exists()){

                  maxid=(snapshot.childrenCount.toInt())

              }
          }

          override fun onCancelled(error: DatabaseError) {
              Toast.makeText(this@mainActivity,"error",Toast.LENGTH_SHORT).show()
          }

      })

      /* if (ref11 != null) {*/
      val music= notes(txtenter,maxid,inienter,finenter)

      val songobj: notes= music
      ref11.child((maxid).toString()).setValue(songobj).addOnCompleteListener {
          if(it.isSuccessful()){
              Toast.makeText(this ,"Song Uploaded",Toast.LENGTH_SHORT).show()


          }
      }.addOnFailureListener {
          Toast.makeText(this ,it.message.toString(),Toast.LENGTH_SHORT).show()
      }



      Toast.makeText(this,"song uploaded",Toast.LENGTH_SHORT).show()

  }



    private fun getuserdata() {
        dbref= FirebaseDatabase.getInstance().getReference("Notes")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                myActivity.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {

                        userSnapshot.getValue(notes::class.java)
                            ?.let { myActivity.add(it) }

                    }
                }


                binding.recy.adapter= notesdAdapter(this@mainActivity, myActivity)


                /*postDao = PostDao()
        val postsCollections = postDao.postCollections
        val query = postsCollections.orderBy("songname", Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Song>().setQuery(query, Song::class.java).build()

        uploadAdapter = PostAdapter(recyclerViewOptions, this)


        binding.recyclerviewid.layoutManager = LinearLayoutManager(this)*/

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@mainActivity,"error", Toast.LENGTH_SHORT).show()
            }

        })



    }

}