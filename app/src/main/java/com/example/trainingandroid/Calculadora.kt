package com.example.trainingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.lang.StringBuilder

class Calculadora : AppCompatActivity() {
    private var numerosActuales: StringBuilder = StringBuilder()
    private var operacion: String = String()
    private var primerValor: Float = 0F
    private var segundoValor: Float = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)
        var simboloC = findViewById<TextView>(R.id.signoC)
        simboloC.setOnClickListener {
            numerosActuales.setLength(0)
            updateView()
        }
        var signoBorrar = findViewById<TextView>(R.id.signoBorrar)
        signoBorrar.setOnClickListener {
            numerosActuales.deleteCharAt(numerosActuales.length -1)
            updateView()
        }

        var simboloIgual = findViewById<TextView>(R.id.simboloIgual)
        simboloIgual.setOnClickListener {
            findSecondValue()
        }
    }

    fun convertCharToInt(charater: View) {
        when (charater.id) {
            R.id.numero0 -> numerosActuales.append('0')
            R.id.numero1 -> numerosActuales.append('1')
            R.id.numero2 -> numerosActuales.append('2')
            R.id.numero3 -> numerosActuales.append('3')
            R.id.numero4 -> numerosActuales.append('4')
            R.id.numero5 -> numerosActuales.append('5')
            R.id.numero6 -> numerosActuales.append('6')
            R.id.numero7 -> numerosActuales.append('7')
            R.id.numero8 -> numerosActuales.append('8')
            R.id.numero9 -> numerosActuales.append('9')
            R.id.simboloPunto -> {
                if(!numerosActuales.contains('.')){
                    numerosActuales.append('.')
                }
            }
        }
        updateView()
    }

    fun convertCharToCorrectOperation(charater: View) {
        if (operacion.isEmpty()) {
            when (charater.id) {
                R.id.signoDividir -> {
                    operacion = "/"
                    primerValor = numerosActuales.toString().toFloat()
                    numerosActuales.append(operacion)
                }

                R.id.signoMultiplicar -> {
                    operacion = "x"
                    primerValor = numerosActuales.toString().toFloat()
                    numerosActuales.append(operacion)
                }

                R.id.signoRestar -> {
                    operacion = "-"
                    primerValor = numerosActuales.toString().toFloat()
                    numerosActuales.append(operacion)
                }
                R.id.signoSumar -> {
                    operacion = "+"
                    primerValor = numerosActuales.toString().toFloat()
                    numerosActuales.append(operacion)
                }
            }
        }
        updateView()
    }

    private fun updateView(){
        var primerValorVisual = findViewById<TextView>(R.id.primerValor)
        primerValorVisual.text = numerosActuales.toString()
    }

    private fun findSecondValue(){
        if(operacion.isNotEmpty() && segundoValor == 0F){
            val posicion = numerosActuales.indexOf(operacion)
            println("Primera posición de $posicion")
            segundoValor = numerosActuales.substring(posicion+1,numerosActuales.length).toFloat()
        }
        resetAllValues()
    }

    private fun resetAllValues(){
        var operacionTotalVista = findViewById<TextView>(R.id.valorTotal)
        when(operacion){
            "x" -> {
                operacionTotalVista.text = (primerValor * segundoValor).toString()
                primerValor = 0F
                segundoValor = 0F
                numerosActuales.setLength(0)
                operacion = ""
            }
            "/" -> {
                operacionTotalVista.text = (primerValor / segundoValor).toString()
                primerValor = 0F
                segundoValor = 0F
                numerosActuales.setLength(0)
                operacion = ""
            }
            "-" -> {
                operacionTotalVista.text = (primerValor - segundoValor).toString()
                primerValor = 0F
                segundoValor = 0F
                numerosActuales.setLength(0)
                operacion = ""
            }
            "+" -> {
                operacionTotalVista.text = (primerValor + segundoValor).toString()
                primerValor = 0F
                segundoValor = 0F
                numerosActuales.setLength(0)
                operacion = ""
            }
        }
        updateView()
    }
}