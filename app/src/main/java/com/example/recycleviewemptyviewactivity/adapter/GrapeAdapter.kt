package com.example.recycleviewemptyviewactivity.adapter

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewemptyviewactivity.DetailGrapeActivity
import com.example.recycleviewemptyviewactivity.R
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class GrapeAdapter() : RecyclerView.Adapter<GrapeAdapter.GrapeViewHolder>() {

    private var grapeList = emptyList<GrapeModel>()

    class GrapeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        init {
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailGrapeActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrapeViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_grape_layoutmodel, parent, false)
        return GrapeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return grapeList.size;
    }

    override fun onBindViewHolder(holder: GrapeViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.sort).text = grapeList[position].sort
        holder.itemView.findViewById<TextView>(R.id.price).text = grapeList[position].price
    }

    fun setList(list: List<GrapeModel>){
        grapeList = list
        notifyDataSetChanged()
    }
}