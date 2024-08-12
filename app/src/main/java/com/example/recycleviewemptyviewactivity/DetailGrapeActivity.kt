package com.example.recycleviewemptyviewactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailGrapeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_grape_layoutmodel)  // Убедитесь, что target.xml находится в папке res/layout

        // Здесь вы можете инициализировать ваши компоненты, например:
        // val textView: TextView = findViewById(R.id.textView)
        // textView.text = "Hello from TargetActivity"
    }

}