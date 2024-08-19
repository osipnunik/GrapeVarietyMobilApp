package com.example.recycleviewemptyviewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewemptyviewactivity.adapter.GrapeAdapter
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO
import com.example.recycleviewemptyviewactivity.databinding.ActivityMainBinding
import com.example.recycleviewemptyviewactivity.model.GrapeModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter : GrapeAdapter
    lateinit var recyclerView: RecyclerView
    var grapeDAO : GrapeDAO = GrapeDAO(this)

    lateinit var grapes : ArrayList<GrapeModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater )
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        recyclerView = binding.grape;
        grapes = grapeDAO.getAllItems()
        adapter = GrapeAdapter(grapes, this)
        recyclerView.adapter = adapter
        grapeDAO.initDB()
        adapter.setList(myGrapes())
    }

    fun myGrapes(): ArrayList<GrapeModel> {
        return grapes
    }
}