package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynotes.databinding.ItemNotesBinding
class itemnotes : AppCompatActivity() {
    private lateinit var binding:ItemNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ItemNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.text


    }
}


