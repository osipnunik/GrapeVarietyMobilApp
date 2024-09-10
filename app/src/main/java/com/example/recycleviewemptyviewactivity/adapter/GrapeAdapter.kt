package com.example.recycleviewemptyviewactivity.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewemptyviewactivity.DetailGrapeActivity
import com.example.recycleviewemptyviewactivity.R
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class GrapeAdapter(private val itemList: List<GrapeModel>, private val listener: RecycleViewEvent/*val context: Context*/) : RecyclerView.Adapter<GrapeAdapter.GrapeViewHolder>() {

    public var grapeList = emptyList<GrapeModel>()
    get() = field
    //private var grapeDAO : GrapeDAO = GrapeDAO(context)
    inner class GrapeViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        val grapeItemView = view.findViewById<TextView>(R.id.sort)
        init {
            view.setOnClickListener(this)//grapeItemView
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
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
        holder.itemView.findViewById<CheckBox>(R.id.fav).isChecked = (grapeList[position].fav == 1)
        holder.itemView.findViewById<CheckBox>(R.id.fav).isClickable = false;
    }

    fun setList(list: List<GrapeModel>){
        grapeList = list
        notifyDataSetChanged()
    }
    interface RecycleViewEvent{
        fun onItemClick(position: Int)
    }
}