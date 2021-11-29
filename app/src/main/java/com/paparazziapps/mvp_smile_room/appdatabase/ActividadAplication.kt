package com.paparazziapps.mvp_smile_room.appdatabase

import android.app.Application


//To call Db
class ActividadAplication: Application() {

    val database:AppDatabase by lazy { AppDatabase.getInstanceAppDatabase(this) }

}