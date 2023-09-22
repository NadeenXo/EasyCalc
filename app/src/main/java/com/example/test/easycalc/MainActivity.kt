package com.example.test.easycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.mozilla.javascript.Context

class MainActivity : AppCompatActivity() {
    // Declare variables for each button

//    private lateinit var btnC: Button
//    private lateinit var btnOpen: Button
//    private lateinit var btnClose: Button
//    private lateinit var btnDivision: Button
//    private lateinit var btn7: Button
//    private lateinit var btn8: Button
//    private lateinit var btn9: Button
//    private lateinit var btnMultiply: Button
//    private lateinit var btn4: Button
//    private lateinit var btn5: Button
//    private lateinit var btn6: Button
//    private lateinit var btnPlus: Button
//    private lateinit var btn1: Button
//    private lateinit var btn2: Button
//    private lateinit var btn3: Button
//    private lateinit var btnSup: Button
//    private lateinit var btnAC: Button
//    private lateinit var btn0: Button
//    private lateinit var btnDot: Button
//    private lateinit var btnEqual: Button

    private lateinit var tvResult: TextView
    private lateinit var tvExp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons by finding them using their IDs

//        btnC = findViewById(R.id.btn_c)
//        btnOpen = findViewById(R.id.btn_open)
//        btnClose = findViewById(R.id.btn_close)
//        btnDivision = findViewById(R.id.btn_division)
//        btn7 = findViewById(R.id.btn_7)
//        btn8 = findViewById(R.id.btn_8)
//        btn9 = findViewById(R.id.btn_9)
//        btnMultiply = findViewById(R.id.btn_mult)
//        btn4 = findViewById(R.id.btn_4)
//        btn5 = findViewById(R.id.btn_5)
//        btn6 = findViewById(R.id.btn_6)
//        btnPlus = findViewById(R.id.btn_plus)
//        btn1 = findViewById(R.id.btn_1)
//        btn2 = findViewById(R.id.btn_2)
//        btn3 = findViewById(R.id.btn_3)
//        btnSup = findViewById(R.id.btn_sup)
//        btnAC = findViewById(R.id.btn_ac)
//        btn0 = findViewById(R.id.btn_0)
//        btnDot = findViewById(R.id.btn_dot)
//        btnEqual = findViewById(R.id.btn_equal)
        tvResult = findViewById(R.id.tv_result)
        tvExp = findViewById(R.id.tv_expression)

        setButtonClickListeners()
    }

    private fun setButtonClickListeners() {
        val buttonIds = arrayOf(
            R.id.btn_c, R.id.btn_open, R.id.btn_close, R.id.btn_division,
            R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_mult,
            R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_plus,
            R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_sup,
            R.id.btn_ac, R.id.btn_0, R.id.btn_dot, R.id.btn_equal
        )

        for (buttonId in buttonIds) {
            findViewById<Button>(buttonId).setOnClickListener {
                val buttonText = (it as Button).text.toString()
                var dataToCalculate = tvExp.text.toString()


                when (buttonText) {
                    "=" -> {
                        getFinalResult(dataToCalculate)
                        dataToCalculate = tvResult.text.toString()
                        setExpressionText(dataToCalculate)
                    }

                    "AC" -> {
                        setResultText("0")
                        setExpressionText("")
                        dataToCalculate = ""
                    }

                    "C" -> {
                        if (dataToCalculate.isNotEmpty()) {
                            dataToCalculate =
                                dataToCalculate.substring(0, dataToCalculate.length - 1)
                            calculateResult(dataToCalculate)
                        }
                    }

                    else -> {
                        dataToCalculate += buttonText
                    }
                }
                setExpressionText(dataToCalculate)
                getFinalResult(dataToCalculate)
            }
        }
    }

    private fun getFinalResult(dataToCalculate: String) {
        var finalResult = calculateResult(dataToCalculate)
        if (finalResult != "Error") {
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "")
            }
            setResultText(finalResult)
        }
    }

    private fun setExpressionText(text: String) {
        tvExp.text = text
    }

    private fun setResultText(text: String) {
        tvResult.text = text
    }

    private fun calculateResult(data: String): String {
        try {
            val context = Context.enter() //enters a JavaScript context
            context.optimizationLevel = -1 //disable JavaScript optimization
            val scriptable = context.initStandardObjects() //initializes a JavaScript scope
            if (data == "") {
                return "0"
            }
            return context.evaluateString(
                scriptable,
                data,
                "Javascript", //name of the source (used for error messages)
                1, //The line number (used for error messages)
                null
            ).toString()

        } catch (e: Exception) {
            return "Error"
        }
    }

//    private fun setButtonClickListeners() {
//        // Set click listeners for each button
//        btnC.setOnClickListener { appendText(btnC.text.toString()) }
//        btnOpen.setOnClickListener { appendText(btnOpen.text.toString()) }
//        btnClose.setOnClickListener { appendText(btnClose.text.toString()) }
//        btnDivision.setOnClickListener { appendText(btnDivision.text.toString()) }
//        btn7.setOnClickListener { appendText(btn7.text.toString()) }
//        btn8.setOnClickListener { appendText(btn8.text.toString()) }
//        btn9.setOnClickListener { appendText(btn9.text.toString()) }
//        btnMultiply.setOnClickListener { appendText(btnMultiply.text.toString()) }
//        btn4.setOnClickListener { appendText(btn4.text.toString()) }
//        btn5.setOnClickListener { appendText(btn5.text.toString()) }
//        btn6.setOnClickListener { appendText(btn6.text.toString()) }
//        btnPlus.setOnClickListener { appendText(btnPlus.text.toString()) }
//        btn1.setOnClickListener { appendText(btn1.text.toString()) }
//        btn2.setOnClickListener { appendText(btn2.text.toString()) }
//        btn3.setOnClickListener { appendText(btn3.text.toString()) }
//        btnSup.setOnClickListener { appendText(btnSup.text.toString()) }
//        btnAC.setOnClickListener { clearInput() }
//        btn0.setOnClickListener { appendText(btn0.text.toString()) }
//        btnDot.setOnClickListener { appendText(btnDot.text.toString()) }
//        btnEqual.setOnClickListener { calculateResult() }
//    }

}

