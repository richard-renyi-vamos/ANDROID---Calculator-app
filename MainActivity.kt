package com.example.colorfulcalculator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var operator: String? = null
    private var firstNumber: Double? = null
    private var secondNumber: Double? = null
    private var colorIndex = 0
    private val colors = arrayOf("#FFEBEE", "#E3F2FD", "#FFFDE7", "#E8F5E9", "#F3E5F5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttons = listOf(
            findViewById<Button>(R.id.button0), findViewById<Button>(R.id.button1),
            // Add buttons 2-9
            findViewById<Button>(R.id.buttonAdd), findViewById<Button>(R.id.buttonSubtract),
            findViewById<Button>(R.id.buttonMultiply), findViewById<Button>(R.id.buttonDivide),
            findViewById<Button>(R.id.buttonEquals)
        )

        buttons.forEach { button ->
            button.setOnClickListener { onButtonClick(button) }
        }

        val changeColorButton: Button = findViewById(R.id.buttonChangeColor)
        changeColorButton.setOnClickListener { changeColor() }
    }

    private fun onButtonClick(button: Button) {
        when (button.text) {
            in "0".."9" -> appendNumber(button.text.toString())
            in listOf("+", "-", "*", "/") -> setOperator(button.text.toString())
            "=" -> calculateResult()
        }
    }

    private fun appendNumber(number: String) {
        if (operator == null) {
            firstNumber = (firstNumber?.toString() ?: "") + number
            display.text = firstNumber.toString()
        } else {
            secondNumber = (secondNumber?.toString() ?: "") + number
            display.text = secondNumber.toString()
        }
    }

    private fun setOperator(op: String) {
        operator = op
    }

    private fun calculateResult() {
        if (firstNumber != null && secondNumber != null && operator != null) {
            val result = when (operator) {
                "+" -> firstNumber!!.toDouble() + secondNumber!!.toDouble()
                "-" -> firstNumber!!.toDouble() - secondNumber!!.toDouble()
                "*" -> firstNumber!!.toDouble() * secondNumber!!.toDouble()
                "/" -> firstNumber!!.toDouble() / secondNumber!!.toDouble()
                else -> 0.0
            }
            display.text = result.toString()
            firstNumber = result
            secondNumber = null
            operator = null
        }
    }

    private fun changeColor() {
        colorIndex = (colorIndex + 1) % colors.size
        findViewById<RelativeLayout>(R.id.rootLayout).setBackgroundColor(Color.parseColor(colors[colorIndex]))
    }
}
