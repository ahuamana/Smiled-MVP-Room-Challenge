package com.paparazziapps.mvp_smile_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_comentarios")
data class Comentario (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codigo")
    var codigo:Int = 0,

    @ColumnInfo(name = "codigo_actividad")
    var codigo_actividad:Int = 1000,

    @ColumnInfo(name = "mensaje")
    var mensaje:String = "none",

    @ColumnInfo(name = "unixtime")
    var unixtime:Long = 1000

        )