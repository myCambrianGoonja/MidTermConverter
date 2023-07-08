package com.example.midtermconverter

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.midtermconverter.databinding.ActivityMainBinding

/* Enums for the conversion for values.
 * The list of values for drop-down and
 * conversion is taken from here.
 */
enum class ConversionType(val label: String, val factor: Double) {
    KM_TO_MI("Kilometers to Miles", 0.621371),
    MI_TO_KM("Miles to Kilometers", 1.60934),
    CM_TO_IN("Centimeters to Inches", 0.393701),
    IN_TO_CM("Inches to Centimeters", 2.54),
    KG_TO_LB("Kilograms to Pounds", 2.20462),
    LB_TO_KG("Pounds to Kilograms", 0.453592),
    G_TO_OZ("Grams to Ounces", 0.035274),
    OZ_TO_G("Ounces to Grams", 28.3495),
    L_TO_CUPS("Liters to Cups", 4.22675),
    CUPS_TO_L("Cups to Liters", 0.236588),
    C_TO_F("Celsius to Fahrenheit", 9.0 / 5.0),
    F_TO_C("Fahrenheit to Celsius", 5.0 / 9.0),
    F_TO_K("Fahrenheit to Kelvin", 5.0 / 9.0),
    K_TO_C("Kelvin to Celsius", -273.15),
    K_TO_F("Kelvin to Fahrenheit", -459.67)
}

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Setup the spinner for selecting the conversion type
        setupSpinner()

        // Setup the calculate button
        setupCalculateButton()
    }

    /*
     * Setup the spinner with conversion types
     */
    private fun setupSpinner() {
        val conversionTypes = ConversionType.values().map { it.label }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.originalUnit.adapter = adapter
    }

    /*
     * Setup the calculate button click listener
     */
    private fun setupCalculateButton() {
        viewBinding.calculateButton.setOnClickListener {
            // Get the selected conversion type
            val selectedConversionType = ConversionType.values()[viewBinding.originalUnit.selectedItemPosition]

            // Get the original value from the input field
            val originalValue = viewBinding.originalValue.text.toString().toDoubleOrNull()

            if (originalValue != null) {
                // Perform the conversion calculation
                val result = originalValue * selectedConversionType.factor
                viewBinding.resultValue.text = "Result: $result"
            } else {
                viewBinding.resultValue.text = "Invalid value"
            }
        }
    }
}
