package com.paparazziapps.mvp_smile_room.appdatabase

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.interfaces.ComentarioDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.models.Comentario


@Database(
    entities = [Actividad::class, Comentario::class], //all models here
    version = 2
)
abstract class AppDatabase: RoomDatabase() {

    //All DAOs interface here
    abstract fun ActividadDao():ActividadDao
    abstract fun ComentarioDao():ComentarioDao

    //Create instante for db
    companion object {

        @Volatile //allow to see
        private var INSTANCE:AppDatabase ? = null

        fun getInstanceAppDatabase(context: Context): AppDatabase{

            synchronized(this){

                var instance:AppDatabase? = INSTANCE

                if(instance==null)
                {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java,"db-school")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return instance

            }



        }
    }
}

