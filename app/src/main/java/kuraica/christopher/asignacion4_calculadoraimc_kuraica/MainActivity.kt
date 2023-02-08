package kuraica.christopher.asignacion4_calculadoraimc_kuraica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Dictionary of ranges
    val ranges = mapOf(
        "Bajo peso" to 0.0..18.5,
        "Normal" to 18.5..24.9,
        "Sobrepeso" to 24.9..29.9,
        "Obesidad grado 1" to 30.0..34.9,
        "Obesidad grado 2" to 35.0..39.9,
        "Obesidad grado 3" to 40.0..100.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the UI elements
        val weight: EditText = findViewById(R.id.weight)
        val height: EditText = findViewById(R.id.height)
        val calculate: Button = findViewById(R.id.calculate)
        val imc: TextView = findViewById(R.id.imc)
        val range: TextView = findViewById(R.id.range)

        // Set event listeners
        calculate.setOnClickListener {
            // make sure the values are not empty
            if (weight.text.isEmpty() || height.text.isEmpty()) {
                imc.text = "Por favor ingrese los valores"
                return@setOnClickListener
            }
            // Assign values to variables and calculate the IMC
            val weightValue = weight.text.toString().toDouble()
            val heightValue = height.text.toString().toDouble()
            val imcValue = calculateIMC(weightValue, heightValue)
            // Set the text of the IMC and range tags
            imc.text = "Tu IMC es: $imcValue"
            range.text = "Tu rango es: ${ranges.filter { it.value.contains(imcValue) }.keys.first()}"
            // Set color of the range tag
            when (imcValue) {
                in 0.0..18.5 -> range.setBackgroundResource(R.color.colorGreenish)
                in 18.5..24.9 -> range.setBackgroundResource(R.color.colorGreen)
                in 24.9..29.9 -> range.setBackgroundResource(R.color.colorYellow)
                in 30.0..34.9 -> range.setBackgroundResource(R.color.colorOrange)
                in 35.0..39.9 -> range.setBackgroundResource(R.color.colorRed)
                in 40.0..100.0 -> range.setBackgroundResource(R.color.colorBrown)
            }
        }

    }
    /**
     * Calculate the IMC
     * @param weight the weight of the person
     * @param height the height of the person
     * @return the IMC of the person
     */
    fun calculateIMC(weight: Double, height: Double): Double {
        return weight / (height * height)
    }
}