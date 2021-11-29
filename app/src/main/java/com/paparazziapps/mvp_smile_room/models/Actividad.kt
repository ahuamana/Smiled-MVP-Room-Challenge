package com.paparazziapps.mvp_smile_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_actividad")
data class Actividad (

    @PrimaryKey(autoGenerate = true)
    val codigo:Int,

    @ColumnInfo(name = "numberRamdon")
    val numberRamdon:Int,

    @ColumnInfo(name = "titulo")
    val titulo:String,

    @ColumnInfo(name = "contenido")
    val contenido:String,

    @ColumnInfo(name = "fecha_inicio")
    val fecha_inicio:Long,

    @ColumnInfo(name = "fecha_fin")
    val fecha_fin:String,

    @ColumnInfo(name = "isCompleted")
    val isCompleted:Boolean

)