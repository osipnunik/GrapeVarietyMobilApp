package com.example.recycleviewemptyviewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewemptyviewactivity.adapter.GrapeAdapter
import com.example.recycleviewemptyviewactivity.databinding.ActivityMainBinding
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter : GrapeAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater )
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        recyclerView = binding.grape;
        adapter = GrapeAdapter()
        recyclerView.adapter = adapter
        adapter.setList(myGrape())
    }

    fun myGrape(): ArrayList<GrapeModel>{
        val grapeList = ArrayList<GrapeModel>()
        val user1 = GrapeModel(1,"Kishmish", "2.10", "")
        grapeList.add(user1)

        val user2 = GrapeModel(2, "izum", "100.10", "")
        grapeList.add(user2)

        return grapeList
    }
}