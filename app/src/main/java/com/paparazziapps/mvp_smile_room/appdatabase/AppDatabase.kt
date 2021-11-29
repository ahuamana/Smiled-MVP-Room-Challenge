package com.paparazziapps.mvp_smile_room.appdatabase

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad


@Database(
    entities = [Actividad::class], //all models here
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    //All DAOs interface here
    abstract fun ActividadDao():ActividadDao

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
                        .build()
                }

                return instance

            }



        }
    }
}

