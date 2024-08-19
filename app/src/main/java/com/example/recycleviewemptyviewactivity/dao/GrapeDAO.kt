package com.example.recycleviewemptyviewactivity.dao

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.example.recycleviewemptyviewactivity.R
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class GrapeDAO(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "grapedb"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private val TABLE_NAME = "mygrape"

        // below variable is for our id column.
        private val ID_COL = "id"

        // below variable is for our sort name column
        private val SORT_COL = "sort"

        // below variable id for our grape price column.
        private val PRICE_COL = "price"

        // below variable description for our grape description column.
        private val DESCRIPTION_COL = "description"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_GRAPES_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY," + SORT_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT"
                + ")")
        db?.execSQL(CREATE_GRAPES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // Оставляем этот метод пустым, если обновление базы данных не требуется
    }

    fun getGrapeBySort(db: SQLiteDatabase?, sort: String): String{
        val GET_GRAPE = "SELECT * FROM " + TABLE_NAME +
                "WHERE sort =" + sort + ")";
        return db?.execSQL(sort).toString();
    }

    fun getGrapeById(db: SQLiteDatabase?, id: Int): String{
        val GET_GRAPE = "SELECT * FROM " + TABLE_NAME +
                "WHERE id =" + id + ")";
        return db?.execSQL(id.toString()).toString();
    }

    @SuppressLint("Range")
    fun getAllItems():ArrayList<GrapeModel>{
        val grapeList:ArrayList<GrapeModel> = ArrayList<GrapeModel>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var grapeId: Int
        var sortName: String
        var price: String
        var description: String
        if (cursor.moveToFirst()) {
            do {
                grapeId = cursor.getInt(cursor.getColumnIndex("id"))
                sortName = cursor.getString(cursor.getColumnIndex("sort"))
                price = cursor.getString(cursor.getColumnIndex("price"))
                description = cursor.getString(cursor.getColumnIndex("description"))
                val grape= GrapeModel(grapeId, sortName, price, description)
                grapeList.add(grape)
            } while (cursor.moveToNext())
        }
        return grapeList
    }

    fun addGrape(grape: GrapeModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID_COL, grape.id)
        contentValues.put(SORT_COL, grape.sort) // GrapeModel sort
        contentValues.put(PRICE_COL, grape.price ) // GrapeModel price
        contentValues.put(DESCRIPTION_COL, grape.description ) // GrapeModel description

        // Inserting Row
        val success = db.insert(TABLE_NAME, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    public fun initDB(){
        //println
        val g1 = GrapeModel(1, "Мускат Гамбургский", "3.98$", context.getString(R.string.Muscat_Gamburg))
        this.addGrape(g1)
        val g2 = GrapeModel(2, "Кишмиш Лучистый", "4.98$", context.getString(R.string.Kishmish_Luchistiy))
        this.addGrape(g2)
        val g3 = GrapeModel(3, "Кодрянка", "2.32$", context.getString(R.string.Kodryanka))
        this.addGrape(g3)
    }

}