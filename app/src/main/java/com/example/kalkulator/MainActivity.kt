package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var OPERATORS: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenerAction()
    }

    private fun calculate(value1:Int, value2:Int) : String {
        var result: Int = 0;
        when(OPERATORS) {
            "+" -> result = value1 + value2
            "-" -> result = value1 - value2
            "x" -> result = value1 * value2
            ":" -> result = value1 / value2
        }
        return  result.toString()
    }

    private fun validate():Boolean {
        if(edit_value1.text.isNullOrEmpty() || edit_value2.text.isNullOrEmpty()) {
            return false
        } else if(OPERATORS.isNullOrEmpty()) {
            return false
        }
        return true
    }

    private fun listenerAction() {
            button_result.setOnClickListener {
                if(validate()) {
                    val value1 = edit_value1.text.toString().toInt()
                    val value2 = edit_value2.text.toString().toInt()
                    result.text = calculate(value1, value2)
                } else {
                    showMessage("Masukkan data dengan benar")
                }
            }

            operators.setOnCheckedChangeListener { group, checkedId ->
                val radioButton = findViewById<RadioButton>(group.checkedRadioButtonId)
                OPERATORS = radioButton.text.toString()
                result.text = "Hasil"
            }
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}