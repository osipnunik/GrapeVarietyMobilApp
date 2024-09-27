package com.akopiants.recycleviewemptyviewactivity.dao

import com.akopiants.recycleviewemptyviewactivity.R
import com.akopiants.recycleviewemptyviewactivity.model.GrapeDetail
import com.akopiants.recycleviewemptyviewactivity.model.GrapeModel

class DBDataInserter(val grapeDAO: GrapeDAO) {

    fun insertData(){
        val grapeList = ArrayList<GrapeModel>(9);
        val grapeDetailList = ArrayList<GrapeDetail>(9)
        val g1 = GrapeModel( "Мускат Гамбургский")
        val det1 = GrapeDetail(grapeDAO.context.getString(R.string.Muscat_Gamburg), "mgambur")
        grapeList.add(g1);grapeDetailList.add(det1)
        val g2 = GrapeModel( "Кишмиш Лучистый")
        val det2 = GrapeDetail(grapeDAO.context.getString(R.string.Kishmish_Luchistiy),"kishmish_lychistyi420w")
        grapeList.add(g2);grapeDetailList.add(det2)
        val g3 = GrapeModel( "Кодрянка")
        val det3 = GrapeDetail(grapeDAO.context.getString(R.string.Kodryanka),"kodryanka")
        grapeList.add(g3);grapeDetailList.add(det3)
        val g4 = GrapeModel( "Аркадия")
        val det4 = GrapeDetail(grapeDAO.context.getString(R.string.arkadia),"arkadia")
        grapeList.add(g4);grapeDetailList.add(det4)
        val g5 = GrapeModel( "Августин")
        val det5 = GrapeDetail(grapeDAO.context.getString(R.string.avgustin),"avgustin")
        grapeList.add(g5);grapeDetailList.add(det5)
        val g6 = GrapeModel( "Юпитер")
        val det6 = GrapeDetail(grapeDAO.context.getString(R.string.jupiter),"jupiter420")
        grapeList.add(g6);grapeDetailList.add(det6)
        val g7 = GrapeModel( "Надежда-АЗОС")
        val det7 = GrapeDetail(grapeDAO.context.getString(R.string.nadegda_azos),"nadegda_azos")
        grapeList.add(g7);grapeDetailList.add(det7)
        val g8 = GrapeModel( "Преображение")
        val det8 = GrapeDetail(grapeDAO.context.getString(R.string.preobragenie),"preobragenie")
        grapeList.add(g8);grapeDetailList.add(det8)
        val g9 = GrapeModel( "Велюр")
        val det9 = GrapeDetail(grapeDAO.context.getString(R.string.velur),"velur")
        grapeList.add(g9);grapeDetailList.add(det9)
        grapeDAO.addGrapes(grapeList, grapeDetailList)
    }

}
