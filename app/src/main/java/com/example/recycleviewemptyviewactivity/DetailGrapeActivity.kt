package com.example.recycleviewemptyviewactivity

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO


class DetailGrapeActivity : AppCompatActivity(){

    private var grapeDAO : GrapeDAO = GrapeDAO(this)//not used
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_grape_layoutmodel)  // Убедитесь, что target.xml находится в папке res/layout

        val itemId = intent.getIntExtra("id", -1)

        val sort = intent.getStringExtra("sort")
        var content = findViewById<TextView>(R.id.sort)
        content.setText(sort)

        val price = intent.getStringExtra("price")
        content = findViewById<TextView>(R.id.price)
        content.setText(price)

        val description = intent.getStringExtra("description")
        content = findViewById<TextView>(R.id.content)
        content.setText(description)
        content.setMovementMethod(ScrollingMovementMethod())

        //val image = intent.getIntExtra("image", -1)
        //Log.d("imagePath: ", image.toString())
        Log.d("package: ", packageName)
           val imageView: ImageView = findViewById(R.id.image)
        val imageName: String? = intent.getStringExtra("image") // this would come from your database
        if (imageName != null) {
            Log.d("imagePath: ", imageName)
        }
        val resId = resources.getIdentifier(imageName, "drawable", packageName)
        if (resId != 0) {
            imageView.setImageResource(resId)
            val bitmap = BitmapFactory.decodeResource(resources, resId)
            imageView.setImageBitmap(bitmap)
        }

        //imageView.setImageResource(R.drawable.mgambur)
        /*val bitmap = BitmapFactory.decodeFile("res/drawable/mgambur.jpg")
        imageView.setImageBitmap(bitmap)*/

        /*Glide.with(this)
            .load(this.getDrawable(image))
            .into(imageView);*/

        //getSupportActionBar().setTitle("Custom Title");
        // Здесь вы можете инициализировать ваши компоненты, например:
        // val textView: TextView = findViewById(R.id.textView)
        // textView.text = "Hello from TargetActivity"
    }

}