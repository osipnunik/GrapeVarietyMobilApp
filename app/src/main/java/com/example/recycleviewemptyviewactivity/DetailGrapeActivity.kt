package com.example.recycleviewemptyviewactivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO

class DetailGrapeActivity : AppCompatActivity(){

    private var grapeDAO : GrapeDAO = GrapeDAO(this)//not used

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_grape_layoutmodel)  // Убедитесь, что target.xml находится в папке res/layout

        val itemId = intent.getIntExtra("item_id", -1)
        val description = intent.getIntExtra("description", -1)
        val content = findViewById(R.id.content) as TextView
        content.setText(description)

        /*val imageView = findViewById(R.id.image) as ImageView;
        imageView.setImageResource(R.drawable.mgambur);*/
        /*Glide.with(this)
            .load("https://vinograd.info/images/stories/picss/mgambur.jpg")
            .into(imageView); */

        //getSupportActionBar().setTitle("Custom Title");
        // Здесь вы можете инициализировать ваши компоненты, например:
        // val textView: TextView = findViewById(R.id.textView)
        // textView.text = "Hello from TargetActivity"
    }

}