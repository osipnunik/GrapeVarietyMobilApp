package com.akopiants.recycleviewemptyviewactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.akopiants.recycleviewemptyviewactivity.adapter.GrapeAdapter
import com.akopiants.recycleviewemptyviewactivity.dao.GrapeDAO
import com.akopiants.recycleviewemptyviewactivity.databinding.ActivityMainBinding
import com.akopiants.recycleviewemptyviewactivity.model.GrapeDetail
import com.akopiants.recycleviewemptyviewactivity.model.GrapeModel

class MainActivity : AppCompatActivity(), GrapeAdapter.RecycleViewEvent {

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

    override fun onResume() {
        super.onResume()
        grapes = grapeDAO.getAllItems()
        adapter.setList(grapes)
    }

    private fun initialise() {
        recyclerView = findViewById(R.id.grape)//binding.grape;
        if(grapeDAO.isEmpty()) {
            grapeDAO.initDB()  //insert data
        }
        grapes = grapeDAO.getAllItems()
        adapter = GrapeAdapter(grapes, this )
        recyclerView.adapter = adapter
        adapter.setList(grapes)
    }

    override fun onItemClick(position: Int) {
        val grape = grapes[position]
        val grapeDet: GrapeDetail? = grapeDAO.getGrapeDetailByGrapeId(grape.id)
        val intent = Intent(this, DetailGrapeActivity::class.java)
        intent.putExtra("id", grape.id)
        intent.putExtra("sort", grape.sort)
        intent.putExtra("fav", grape.fav)

        intent.putExtra("description", grapeDet?.description)
        intent.putExtra("image", grapeDet?.image)
        this.startActivity(intent)
    }
}