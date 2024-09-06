package com.example.recycleviewemptyviewactivity.dao

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.recycleviewemptyviewactivity.R
import com.example.recycleviewemptyviewactivity.model.GrapeDetail
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class GrapeDAO(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "grapedb"

        // below int is our database version
        private const val DB_VERSION = 3

        // below variable is for our table name.
        private val TABLE_NAME = "mygrape"

        private val TABLE_DETAIL_NAME = "grape_detail"

        // below variable is for our id column.
        private val ID_COL = "id"

        private val ID_DET_COL = "id"

        // below variable is for our sort name column
        private val SORT_COL = "sort"

        // below variable id for our grape price column.
        private val PRICE_COL = "price"

        // below variable description for our grape description column.
        private val DESCRIPTION_COL = "description"

        // below variable image
        private val IMAGE_COL = "image"

        private val FOREYN_KEY = "grape_foreign_id"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("PRAGMA foreign_keys = ON;")
        val CREATE_GRAPES_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SORT_COL + " TEXT,"
                + PRICE_COL + " TEXT);")
        db?.execSQL(CREATE_GRAPES_TABLE)
        val CREATE_GRAPES_DETAIL = ("CREATE TABLE " + TABLE_DETAIL_NAME + "("
                + ID_DET_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DESCRIPTION_COL + " TEXT,"
                + IMAGE_COL + " TEXT,"
                + FOREYN_KEY + " INTEGER,"
                + " FOREIGN KEY (" + FOREYN_KEY + ") REFERENCES " + TABLE_NAME + "(id)"
                + ");")
        db?.execSQL(CREATE_GRAPES_DETAIL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        // Оставляем этот метод пустым, если обновление базы данных не требуется
    }

    fun getGrapeBySort(db: SQLiteDatabase?, sort: String): GrapeModel{
        val GET_GRAPE = "SELECT * FROM " + TABLE_NAME +
                "WHERE sort =" + sort + ")";
        db?.execSQL(GET_GRAPE, null);
        return GrapeModel("", "")
    }

    fun getGrapeById(id: Int): GrapeModel?{
        val GET_GRAPE = "SELECT * FROM $TABLE_NAME WHERE id = $id";
        val db = this.readableDatabase
        val cursor = db.rawQuery(GET_GRAPE, null)
        return if (cursor.moveToFirst()) {
            val grape = GrapeModel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_COL)),
                sort = cursor.getString(cursor.getColumnIndexOrThrow(SORT_COL)),
                price = cursor.getString(cursor.getColumnIndexOrThrow(PRICE_COL))
            )
            cursor.close()
            grape
        } else {
            cursor.close()
            return null // Если обьект не найден
        }
    }

    fun getGrapeDetailByGrapeId(id: Int): GrapeDetail? {
        val GET_GRAPE_DET_QUERY = "SELECT * FROM $TABLE_DETAIL_NAME WHERE $FOREYN_KEY=$id";
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(GET_GRAPE_DET_QUERY, null)
        return if (cursor.moveToFirst()) {
            val grapeDet = GrapeDetail(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_DET_COL)),
                description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_COL)),
                image = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_COL)),
                )
            cursor.close()
            grapeDet
        }else {
            cursor.close()
            return null // Если обьект не найден
        }
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
        if (cursor.moveToFirst()) {
            do {
                grapeId = cursor.getInt(cursor.getColumnIndex("id"))
                sortName = cursor.getString(cursor.getColumnIndex("sort"))
                price = cursor.getString(cursor.getColumnIndex("price"))
                val grape = GrapeModel(grapeId, sortName, price)
                grapeList.add(grape)
            } while (cursor.moveToNext())
        }
        return grapeList
    }

    fun isDetailTableExists(): Boolean{
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='$TABLE_DETAIL_NAME'", null)
        val tableExists = cursor.count > 0
        cursor.close()
        return tableExists
    }

    fun addGrape(grape: GrapeModel, grapeDetail: GrapeDetail):Long?{
        val db = this.writableDatabase
        val grapeContentValues = ContentValues()
        val detailContentValues = ContentValues()

        grapeContentValues.put(SORT_COL, grape.sort) // GrapeModel sort
        grapeContentValues.put(PRICE_COL, grape.price) // GrapeModel price
        detailContentValues.put(DESCRIPTION_COL, grapeDetail.description) // GrapeDetailModel description
        detailContentValues.put(IMAGE_COL, grapeDetail.image) // GrapeDetailModel image

        var successDet: Long? = null
        try {
            // Вставляем данные в основную таблицу и получаем ID
            val insertedId = db.insert(TABLE_NAME, null, grapeContentValues)

            if (insertedId != -1L) {
                // Устанавливаем внешний ключ для таблицы деталей
                detailContentValues.put(FOREYN_KEY, insertedId)
                // Вставляем данные в таблицу деталей
                successDet = db.insert(TABLE_DETAIL_NAME, null, detailContentValues)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close() // Закрываем базу данных
        }
        return successDet
    }

    public fun initDB(){
        //println
        val g1 = GrapeModel( "Мускат Гамбургский", "3.98$")
        val det1 = GrapeDetail(context.getString(R.string.Muscat_Gamburg), "mgambur")
        this.addGrape(g1, det1)
        val g2 = GrapeModel( "Кишмиш Лучистый", "4.98$")
        val det2 = GrapeDetail(context.getString(R.string.Kishmish_Luchistiy),"kishmish_lychistyi420w")
        this.addGrape(g2, det2)
        val g3 = GrapeModel( "Кодрянка", "2.32$")
        val det3 = GrapeDetail(context.getString(R.string.Kodryanka),"kodrjankapng320420")
        this.addGrape(g3, det3)
    }

}