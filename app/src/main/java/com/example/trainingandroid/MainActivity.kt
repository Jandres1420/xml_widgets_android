package com.example.trainingandroid

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        find view by id segun el id qe hayamos puesto en el activity
        var tvEjemplo = findViewById<TextView>(R.id.tvMillos).apply {
            setText("Nacional Campeon")
            setTextColor(Color.GREEN)
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
        }
        // set On es un evento, posibles acciones o
        tvEjemplo.setOnClickListener{
            //toast es como un mini pop up
            // para que se declare en este momento y se vea en pantalla, el tercer parametro es para que tenga
            // una duración corta en pantalla y que lo muestre
            Toast.makeText(this,"JAJAJA te la creiste, Solo millos lok", Toast.LENGTH_SHORT).show()
            tvEjemplo.setText("Millos campeon")
            tvEjemplo.setTextColor(Color.BLUE)
        }

        var editEjemplo = findViewById<EditText>(R.id.editEjemplo)
        editEjemplo.addTextChangedListener{
            //set error pone una advertencia de que el camp esta vacio
            if(editEjemplo.text.length==0) editEjemplo.setError("Campo vacio")
            var inicio = editEjemplo.selectionStart
            var fin = editEjemplo.selectionEnd
//            selecciona todo lo que haya puesto la persona
//             var all = editEjemplo.selectAll()
            editEjemplo.setTextColor(Color.MAGENTA)
        }
        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // de esta forma se inicializa un array teniendo en cuenta que esta en resource.string.xml
        var countries:Array<String> = resources.getStringArray(R.array.countries_array)
        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, countries)

        autoCompleteTextView.setAdapter(adapter)
        var multiAutoCompleteTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoCompleteTextView.setAdapter(adapter)
        // si no se le ponen algo es igual que el autocompletetextview
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

    }
}