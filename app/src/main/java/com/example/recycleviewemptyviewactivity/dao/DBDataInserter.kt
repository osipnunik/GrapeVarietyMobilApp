package com.example.recycleviewemptyviewactivity.dao

import com.example.recycleviewemptyviewactivity.R
import com.example.recycleviewemptyviewactivity.model.GrapeDetail
import com.example.recycleviewemptyviewactivity.model.GrapeModel

class DBDataInserter(val grapeDAO: GrapeDAO) {

    fun insertData() {
        val g1 = GrapeModel( "Мускат Гамбургский")
        val det1 = GrapeDetail(grapeDAO.context.getString(R.string.Muscat_Gamburg), "mgambur")
        grapeDAO.addGrape(g1, det1)
        val g2 = GrapeModel( "Кишмиш Лучистый")
        val det2 = GrapeDetail(grapeDAO.context.getString(R.string.Kishmish_Luchistiy),"kishmish_lychistyi420w")
        grapeDAO.addGrape(g2, det2)
        val g3 = GrapeModel( "Кодрянка")
        val det3 = GrapeDetail(grapeDAO.context.getString(R.string.Kodryanka),"kodrjankapng320420")
        grapeDAO.addGrape(g3, det3)
        val g4 = GrapeModel( "Аркадия")
        val det4 = GrapeDetail(grapeDAO.context.getString(R.string.arkadia),"arkadia")
        grapeDAO.addGrape(g4, det4)
        val g5 = GrapeModel( "Августин")
        val det5 = GrapeDetail(grapeDAO.context.getString(R.string.avgustin),"avgustin")
        grapeDAO.addGrape(g5, det5)
        val g6 = GrapeModel( "Юпитер")
        val det6 = GrapeDetail(grapeDAO.context.getString(R.string.jupiter),"jupiter420")
        grapeDAO.addGrape(g6, det6)
        val g7 = GrapeModel( "Надежда-АЗОС")
        val det7 = GrapeDetail(grapeDAO.context.getString(R.string.nadegda_azos),"nadegda_azos")
        grapeDAO.addGrape(g7, det7)
        val g8 = GrapeModel( "Преображение")
        val det8 = GrapeDetail(grapeDAO.context.getString(R.string.preobragenie),"preobragenie")
        grapeDAO.addGrape(g8, det8)
        val g9 = GrapeModel( "Велюр")
        val det9 = GrapeDetail(grapeDAO.context.getString(R.string.velur),"velur")
        grapeDAO.addGrape(g9, det9)
    }

}
