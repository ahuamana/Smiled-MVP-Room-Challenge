package com.paparazziapps.mvp_smile_room.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.paparazziapps.mvp_smile_room.R
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModel
import com.paparazziapps.mvp_smile_room.adapters.ActividadAdapter
import com.paparazziapps.mvp_smile_room.databinding.ActivityMainBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding


    lateinit var mAdapter: ActividadAdapter
    lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //all code here
        showAllActividades()

        binding.fabAdd.setOnClickListener {
            showDialog()
        }


    }


    private fun showAllActividades() {

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter = ActividadAdapter(this@MainActivity)
            adapter= mAdapter
            val divider = DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(divider)
        }
    }

    private fun showDialog() {

        lateinit var actividad: Actividad
        val dialog = Dialog(this@MainActivity)

        var binding2: CardviewAddActivityBinding
        binding2 = CardviewAddActivityBinding.inflate(LayoutInflater.from(applicationContext))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(binding2.root)

        binding2.cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        binding2.okButton.setOnClickListener {

        }

        dialog.show()
    }

}