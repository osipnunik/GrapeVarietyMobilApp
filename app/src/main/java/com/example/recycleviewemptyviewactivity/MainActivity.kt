package com.example.recycleviewemptyviewactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewemptyviewactivity.adapter.GrapeAdapter
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO
import com.example.recycleviewemptyviewactivity.databinding.ActivityMainBinding
import com.example.recycleviewemptyviewactivity.model.GrapeDetail
import com.example.recycleviewemptyviewactivity.model.GrapeModel
import java.util.ArrayList

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

    override fun onDestroy() {
        this.deleteDatabase("grapedb");
        super.onDestroy()
    }

    private fun initialise() {
        recyclerView = findViewById(R.id.grape)//binding.grape;
        if(grapeDAO.isEmpty()) {
            grapeDAO.initDB()  //insert data
        }
        grapes = grapeDAO.getAllItems()
        adapter = GrapeAdapter(grapes, this )
        recyclerView.adapter = adapter
        Log.d("listSize", "lisy size is: "+grapes.size)
        adapter.setList(grapes)
    }

    override fun onItemClick(position: Int) {
        val grape = grapes[position]
        val grapeDet: GrapeDetail? = grapeDAO.getGrapeDetailByGrapeId(grape.id)
        Log.i("debugging, started to work", "Table exists"+grapeDAO.isDetailTableExists())
        //Toast.makeText(this, grape.sort, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailGrapeActivity::class.java)
        intent.putExtra("id", grape.id)
        intent.putExtra("sort", grape.sort)
        intent.putExtra("fav", grape.fav)

        intent.putExtra("description", grapeDet?.description)
        intent.putExtra("image", grapeDet?.image)
        this.startActivity(intent)
    }
}