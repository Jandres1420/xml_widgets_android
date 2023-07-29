package com.example.trainingandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Buttons : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        var buttonOrange = findViewById<Button>(R.id.buttonOrange)

        buttonOrange.setOnClickListener{
            Toast.makeText(this,"Oprimiendo boton naranja", Toast.LENGTH_SHORT).show()
        }

        var imageButton = findViewById<ImageButton>(R.id.imageButton)

        imageButton.setOnClickListener{
            Toast.makeText(this,"Oprimiendo boton con imagen", Toast.LENGTH_SHORT).show()
        }
        // Todo lo que tiene que ver con Chip group y Chips
        var chgroup = findViewById<ChipGroup>(R.id.chgroup)

        val chip = Chip(this)
        chip.text = "Gaming"
        // esto es necesario para tener single selection  working
        chip.isClickable = true
        chip.isCheckable = false
        var styleChip = R.style.chipGroupEntry
        var drawableStyle = ChipDrawable.createFromAttributes(this,null,0,styleChip)
        chip.setChipDrawable(drawableStyle)
        chip.chipIcon = ContextCompat.getDrawable(this,R.drawable.nintendo_game_boy)
        chgroup.addView(chip as View)
        chip.setOnCloseIconClickListener {
            chgroup.removeView(chip as View)
        }

        var child:Chip
        for( i in 0..chgroup.childCount-1){
            child = chgroup.getChildAt(i) as Chip
            child.setOnCloseIconClickListener{
                chgroup.removeView(it)
            }

            child.setOnClickListener{
                var aux = it as Chip
                Toast.makeText(this,"${aux.text}  pulsado", Toast.LENGTH_SHORT).show()
            }
        }

        var radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
// tambien se puede hacer de esta manera var radioGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
        var rb = radioGroup.getChildAt(1)  as RadioButton
        // esto es para checkear en el radio group uno en especifico
        radioGroup.check(rb.id)

        var cbSeguro = findViewById<CheckBox>(R.id.cbSeguro)
        cbSeguro.isEnabled = true
        cbSeguro.isChecked = true

        var toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        // mirar si cambia el estado en toggleButton
        toggleButton.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked)    Toast.makeText(this,"Toggle Activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Toggle Desactivado", Toast.LENGTH_SHORT).show()
        }

        var swNormal = findViewById<Switch>(R.id.swNormal)
        swNormal.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked)    Toast.makeText(this,"Switch Activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Switch Desactivado", Toast.LENGTH_SHORT).show()
        }

        var fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this, WidgetsD::class.java)
            startActivity(intent)
        }

    }
//    Esto esta haciendo referencia al xml onClick y tiene que ser publica y además tener el mismo nombre,
//    y tambien muy importante a pesar de saaber que es un CheckButton tiene que recibir una vista porque es la padre y es generico
//    ya en el codigo como tal se casteara
    fun onRadioButtonClicked(view:View){
        if(view is RadioButton){
//            como se guarda el estado
            var checked = view.isChecked

            when(view.getId()){
                R.id.radioButton -> {  Toast.makeText(this,"Vamos a la playa", Toast.LENGTH_SHORT).show() }
                R.id.radioButton2 -> {  Toast.makeText(this,"Vamos a la montaña", Toast.LENGTH_SHORT).show() }
            }
        }
    }

    fun onCheckBoxButtonClicked(view:View){
        if(view is CheckBox){
//            como se guarda el estado
            var checked = view.isChecked

            when(view.getId()){
                R.id.cbSeguro -> {  if (checked) Toast.makeText(this,"Seguro contratado", Toast.LENGTH_SHORT).show()
                else  Toast.makeText(this,"Seguro Anulado", Toast.LENGTH_SHORT).show() }
                R.id.cbCancelable -> { if (checked)  Toast.makeText(this,"La reserva podra ser cancelada", Toast.LENGTH_SHORT).show()
                else Toast.makeText(this,"La reserva no podra ser cancelada", Toast.LENGTH_SHORT).show()}
            }
        }
    }
}