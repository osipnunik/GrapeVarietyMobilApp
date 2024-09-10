package com.example.recycleviewemptyviewactivity

import android.app.Application
import com.example.recycleviewemptyviewactivity.dao.GrapeDAO

class App : Application() {
    //lateinit var grapeDAO: GrapeDAO
    /*override fun onCreate() {
        super.onCreate()
        grapeDAO = GrapeDAO(getApplicationContext())
        if(grapeDAO.isEmpty()) {
            grapeDAO.initDB()  //insert data
        }
    }*/
}