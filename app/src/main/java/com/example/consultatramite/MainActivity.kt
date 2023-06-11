package com.example.consultatramite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.consultatramite.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var datos:String
    private lateinit var tolls: Toolbar
    var isDeletePressed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        // tolls = findViewById(R.id.mytoolbar)
        //tolls.setTitle("APP QR ")
        //setSupportActionBar(tolls)
        binding.expediente.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var text=binding.expediente.text.toString()
                if(text.length ==4) {
                    if (!isDeletePressed && text.length !=5 ) {
                        binding.expediente.setText(binding.expediente.text.toString() + "-")
                        binding.expediente.setSelection(binding.expediente.text.toString().length)
                    }
                }
            }

        } )
        binding.findestado.setOnClickListener(){
            //val intent = Intent(this, Scan_ciudadano::class.java)
            //startActivity(intent)

            var expediente = binding.expediente.text.toString().replace("\\s".toRegex(), "")
            var ruc = binding.ruc.text.toString().replace("\\s".toRegex(), "")
            obtenerEstadoTramite(expediente,ruc)


        }
        binding.expediente.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(view: View?, i: Int, keyEvent: KeyEvent?): Boolean {
                isDeletePressed = i == KeyEvent.KEYCODE_DEL
                return false
            }
        })


    }
    private fun obtenerEstadoTramite(expediente:String, ruc:String){
        val check= checkinternet1()
        var url1 = "exec?spreadsheetId=1Isxv6jSuTXz-FRm6LcdWHXmi7z6xm3o40vfaNiu3oD8&sheet=Hoja1&expediente=$expediente&ruc=$ruc"

        //if(ParametroTramite.expediente.equals(expediente) && ParametroTramite.ruc.equals(ruc)){
        if(false){
            initActivity()
        }else {
            if (check.checkForInternet(this)) {
                CoroutineScope(Dispatchers.IO).launch {
                    val calls = getRetrofit().create(apiService::class.java)
                        .getValidUser(url1)
                    val tramite = calls.body()
                    runOnUiThread {

                        if (calls != null) {
                            ParametroTramite.expediente = tramite?.expediente ?: "No hay user"
                            ParametroTramite.ruc = tramite?.ruc ?: "No hay user"
                            ParametroTramite.razonsocial = tramite?.razon_social ?: "No hay user"
                            ParametroTramite.fechanacimiento = tramite?.fecha_nac ?: "No hay user"
                            ParametroTramite.expediente = tramite?.expediente ?: "No hay user"
                            ParametroTramite.Direccion = tramite?.dirrec ?: "No hay user"
                            ParametroTramite.Distrito = tramite?.distrito ?: "No hay user"
                            ParametroTramite.correo = tramite?.correo ?: "No hay user"
                            ParametroTramite.Estadoexp = tramite?.estadoexp ?: "No hay user"
                            ParametroTramite.ErrorTramite = tramite?.errortra ?: "No hay error"
                            if (ParametroTramite.expediente.equals(expediente) && ParametroTramite.ruc.equals(
                                    ruc
                                )
                            )
                                initActivity()
                            else {
                                val passwordLayout: TextInputLayout =
                                    findViewById(R.id.textInputLayout2)
                                passwordLayout.error = "Datos incorrectos"
                                val passwordLayout2: TextInputLayout =
                                    findViewById(R.id.textInputLayout3)
                                passwordLayout2.error = "Datos incorrectos"
                            }
                        } else {

                            showToast("Error al validar usuario")

                        }
                    }
                }
                // some code
            } else {
                Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/AKfycbx7RGsbELIGYHBtLYa1KyfelwUGNFgo3Sn_w5NrvjQDDZ0tJz8nWV_CEGYN15epdOg/")
            //.baseUrl("https://delorekbyrnison.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun initActivity() {
        val i = Intent(this,RequestActivity::class.java)
        val passwordLayout: TextInputLayout = findViewById(R.id.textInputLayout2)
        passwordLayout.error = null
        val passwordLayout2: TextInputLayout = findViewById(R.id.textInputLayout3)
        passwordLayout2.error = null
        startActivity(i)

    }
    private fun showToast(dato1:String){
        Toast.makeText(this,dato1, Toast.LENGTH_SHORT).show()
    }

}