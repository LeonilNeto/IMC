package com.example.mykotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalcular.setOnClickListener {
            try {
                val altura = edtAltura.text.toString().toDouble()
                val peso = edtPeso.text.toString().toDouble()
                if(altura  == 0.0 || peso == 0.0) {
                    txtResultado.setText("Impossível divisão por zero! ")
                } else {
                    val imc = calcIMC(altura, peso)
                    val df = DecimalFormat("#.00")
                    val imcResp = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
                    txtResultado.setText(imcResp)
                }
            } catch (e: NumberFormatException) {
                txtResultado.setText(R.string.valoresInvalidos)
            }
            it.hideKeyboard()
        }
    }
        private fun calcIMC(altura: Double, peso: Double): Double = peso / (altura * altura)

        private fun checkIMC(db: Double): String {
            return when(db) {
                in 0.0..17.0 -> "Muito abaixo do peso."
                in 17.1..18.49 -> "Abaixo do peso."
                in 18.5..24.99 -> "Peso normal."
                in 25.0..29.99 -> "Acima do peso."
                in 30.0..34.99 -> "Obesidade I."
                in 35.0..39.99 -> "Obesidade II (severa)."
                else -> "Obesidade III (mórbida)"
            }
        }

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
}