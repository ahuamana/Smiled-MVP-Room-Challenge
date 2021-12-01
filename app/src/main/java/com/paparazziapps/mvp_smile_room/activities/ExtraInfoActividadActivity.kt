package com.paparazziapps.mvp_smile_room.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paparazziapps.mvp_smile_room.R
import com.paparazziapps.mvp_smile_room.databinding.ActivityExtraInfoActividadBinding


class ExtraInfoActividadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraInfoActividadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraInfoActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}