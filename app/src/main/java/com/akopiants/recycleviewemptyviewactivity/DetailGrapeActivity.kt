package com.akopiants.recycleviewemptyviewactivity

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.akopiants.recycleviewemptyviewactivity.dao.GrapeDAO


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

        content = findViewById<CheckBox>(R.id.fav)
        val fav = intent.getIntExtra("fav", -1)
        (content as CheckBox).isChecked = (fav == 1)
        content.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    grapeDAO.flipFlopFavoritesGrape(itemId)
                    Toast.makeText(getApplicationContext(),"избранность сорта $sort изменена", Toast.LENGTH_LONG).show();
                }
            })

        val description = intent.getStringExtra("description")
        content = findViewById<TextView>(R.id.content)
        content.setText(description)
        content.setMovementMethod(ScrollingMovementMethod())

        //val image = intent.getIntExtra("image", -1)
        //Log.d("imagePath: ", image.toString())
        Log.d("package: ", packageName)
        val imageView: ImageView = findViewById(R.id.image)
        /*val imageName: String? =
        intent.getStringExtra("image") // this would come from your database
        val resId = resources.getIdentifier(imageName, "drawable", packageName)
        imageView.setBackgroundResource(resId)*/

        /*if (resId != 0) {
            imageView.setBackgroundResource(resId)
            val bitmap = BitmapFactory.decodeResource(resources, resId)
            imageView.setImageBitmap(bitmap)
        }*/
        val imageName: String? = intent.getStringExtra("image") // Получаем имя изображения из интента
        val resId = resources.getIdentifier(imageName, "drawable", packageName)

        if (resId != 0) {
            imageView.setImageResource(resId) // Меняем изображение
        }
        var isPicture: Boolean = true
        imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (!isPicture) {
                    val imageName: String? = intent.getStringExtra("image") // Получаем имя изображения из интента
                    val resId = resources.getIdentifier(imageName, "drawable", packageName)

                    if (resId != 0) {
                        (v as ImageView).setImageResource(resId) // Меняем изображение
                    }
                    content.maxLines=content.maxLines/2
                } else {
                    (v as ImageView).setBackgroundResource(0)
                    content.maxLines=content.maxLines*2
                    (v as ImageView).setImageResource(R.drawable.icons_arrow_down) // Возвращаем иконку
                }
                isPicture = !isPicture // Переключаем значение переменной
            }
        })
    }
}