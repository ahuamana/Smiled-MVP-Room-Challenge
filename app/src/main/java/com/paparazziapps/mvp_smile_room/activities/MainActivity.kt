package com.paparazziapps.mvp_smile_room.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModel
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModelFactory
import com.paparazziapps.mvp_smile_room.adapters.Adapter
import com.paparazziapps.mvp_smile_room.appdatabase.ActividadAplication
import com.paparazziapps.mvp_smile_room.databinding.ActivityMainBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var mAdapter: Adapter

    private lateinit var viewModel2: MainActivityViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var actividad: Actividad

    private lateinit var repository: ActividadRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //all code here

        repository = ActividadRepository((application as ActividadAplication).database.ActividadDao())

        viewModel2 = ViewModelProvider(
            this,
            MainActivityViewModelFactory(repository)
        ).get(MainActivityViewModel::class.java)


        showAllActividades()


        binding.fabAdd.setOnClickListener {
            showDialog()
        }


    }


    private fun showAllActividades() {
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        mAdapter = Adapter({})

        recyclerView.adapter = mAdapter

        lifecycle.coroutineScope.launch {
            viewModel2.allactividades().collect{
                mAdapter.submitList(it)
            }
        }


    }

    private fun showDialog() {


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

            actividad = Actividad()
            actividad.titulo = binding2.titulo.text.toString()

            lifecycle.coroutineScope.launch {
                viewModel2.insert(actividad)
                dialog.dismiss()
            }


        }

        dialog.show()
    }

}