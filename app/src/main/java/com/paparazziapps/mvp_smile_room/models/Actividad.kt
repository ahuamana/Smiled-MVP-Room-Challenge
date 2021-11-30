package com.paparazziapps.mvp_smile_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_actividad")
data class Actividad (

    @PrimaryKey(autoGenerate = true)
    var codigo:Int = 0,

    @ColumnInfo(name = "numberRamdon")
    var numberRamdon:Int = 0 ,

    @ColumnInfo(name = "titulo")
    var titulo:String = "none",

    @ColumnInfo(name = "contenido")
    var contenido:String = "none",

    @ColumnInfo(name = "fecha_inicio")
    var fecha_inicio:Long = 1000,

    @ColumnInfo(name = "fecha_fin")
    var fecha_fin:String = "none",

    @ColumnInfo(name = "isCompleted")
    var isCompleted:Boolean= false

)