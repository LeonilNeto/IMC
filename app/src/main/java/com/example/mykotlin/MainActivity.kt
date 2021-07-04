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
                    txtResultado.setText(R.string.impossivelDividirPorZero)
                } else {
                    val imc = calcIMC(altura, peso)
                    val df = DecimalFormat("#.00")
                    val imcResp = "IMC: " + df.format(imc) + " - " + checkIMC(imc)
                    txtResultado.text = imcResp
                }
            } catch (e: NumberFormatException) {
                txtResultado.setText(R.string.valoresInvalidos)
            }
            it.hideKeyboard()
        }
    }

    private fun calcIMC(altura: Double, peso: Double): Double = peso / (altura * altura)

    private fun checkIMC(db: Double): String {
        val array: Array<String> = resources.getStringArray(R.array.imc)
        return when(db) {
            in 0.0..17.0 -> array[0]
            in 17.1..18.49 -> array[1]
            in 18.5..24.99 -> array[2]
            in 25.0..29.99 -> array[3]
            in 30.0..34.99 -> array[4]
            in 35.0..39.99 -> array[5]
            else -> array[6]
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
    }
}