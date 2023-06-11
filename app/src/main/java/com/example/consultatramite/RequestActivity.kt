package com.example.consultatramite

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.consultatramite.databinding.ActivityRequestBinding
import java.util.ArrayList

class RequestActivity : AppCompatActivity() {
    private lateinit var   binding: ActivityRequestBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar
    private val mDataList = ArrayList<TimeLineModel>()


    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)

        setContentView(binding.root)
        tolls = findViewById(R.id.topAppBar2)
        binding.hola.setText("Hola, ${ParametroTramite.razonsocial}")
        //getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.baseline_arrow_left_24)
        //getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
        tolls.setNavigationOnClickListener(){


            finish()

        }
        if(ParametroTramite.Estadoexp.equals("Listo")){
            binding.Listo.setTextColor(Color.parseColor("#ffffff"))
            binding.Listo.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable(R.drawable.twotone_check_circle_24_active),null,null,null)

        }
        else{
            binding.Listo.setTextColor(Color.parseColor("#857D7D"))
        }

        if(ParametroTramite.Estadoexp.equals("Pendiente")){
            binding.Progreso.setTextColor(Color.parseColor("#ffffff"))
            binding.Progreso.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable(R.drawable.twotone_build_circle_24_active),null,null,null)

        }
        else{
            binding.Progreso.setTextColor(Color.parseColor("#857D7D"))

        }

        if(ParametroTramite.Estadoexp.equals("Con observaciones")){
            binding.Observacion.setTextColor(Color.parseColor("#ffffff"))
            binding.Observacion.setCompoundDrawablesWithIntrinsicBounds( getResources().getDrawable(R.drawable.twotone_remove_circle_24_active),null,null,null)
        }
        else{
            binding.Observacion.setTextColor(Color.parseColor("#857D7D"))
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }



    private fun setDataListItems() {
        mDataList.add(TimeLineModel("En proceso", "", OrderStatus.COMPLETED))
        mDataList.add(TimeLineModel("Con observaciones", "", OrderStatus.ACTIVE))
        mDataList.add(TimeLineModel("Listo para recoger", "2017-02-11 21:00", OrderStatus.INACTIVE))
    }



}





