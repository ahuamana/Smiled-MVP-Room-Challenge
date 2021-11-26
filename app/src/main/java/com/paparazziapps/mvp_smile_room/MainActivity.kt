package com.paparazziapps.mvp_smile_room

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.paparazziapps.mvp_smile_room.databinding.ActivityMainBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {

        var binding2:CardviewAddActivityBinding
            binding2 = CardviewAddActivityBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding2.root)

        binding2.cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        binding2.okButton.setOnClickListener {
            Log.e("TAG","Crear Tarea")
        }

        dialog.show()


    }
}