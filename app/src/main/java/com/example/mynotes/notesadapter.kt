package com.example.mynotes

import android.animation.ObjectAnimator
import com.google.firebase.database.R



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.databinding.ItemNotesBinding


import com.google.firebase.database.DatabaseReference



import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList


class notesdAdapter (private val context: Context, private var myList:ArrayList<notes>) : RecyclerView.Adapter<notesdAdapter.MyHolder>() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    companion object{
        var size:Int=0

    }


    class MyHolder(binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {






        val title = binding.text
         val ini= binding.initial
        val fin= binding.finaly
      val img=binding.edit

        var pro=binding.probar



    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()


        return MyHolder(ItemNotesBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: MyHolder, position: Int) {


        val userid = mAuth.currentUser?.uid
        holder.title.text=myList[position].notestxt
        holder.ini.text= myList[position].inctxt.toString()
        holder.fin.text=myList[position].dectxt.toString()

        var post=myList[position]

         holder.img.setOnClickListener {
            setintetnonclickitem(ref = "notesadapter", pos = position)
        }
        var audioid=myList[position].id
        holder.pro.max=  holder.fin.text.toString().toInt()
        ObjectAnimator.ofInt(holder.pro,"progress",  holder.ini.text.toString().toInt()!!).setDuration(2000).start()




    }




    // return the number of the items in the list
    override fun getItemCount(): Int {
        size=myList.size
        return myList.size
    }


   private fun setintetnonclickitem(ref:String,pos:Int)
    {
        val intent= Intent(context, newac::class.java)
        intent.putExtra("index",pos)
        intent.putExtra("class",ref)
        ContextCompat.startActivity(context,intent,null)
    }


}