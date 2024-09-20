package com.akopiants.recycleviewemptyviewactivity

import android.app.Application
import com.akopiants.recycleviewemptyviewactivity.dao.GrapeDAO

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