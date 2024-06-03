package chinchillas.diego.asignacion4_calculadoraimc_chinchillas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var imc:Double = 0.0
    var range = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val estatura : EditText = findViewById(R.id.estatura)
        val peso : EditText = findViewById(R.id.kilos)
        val botonIMC : Button = findViewById(R.id.calcular)
        val textIMC : TextView = findViewById(R.id.imc)
        val textRange : TextView = findViewById(R.id.range)

        botonIMC.setOnClickListener{
            val pesoD:Double = peso.text.toString().toDouble()
            val estaturaD:Double = estatura.text.toString().toDouble()
            imc = generarIMC(pesoD, estaturaD)

            textIMC.setText(imc.toString())
            var rangoString = generarCategoria(imc)

            textRange.setText(rangoString)

            if (textRange.text.toString() == "Bajo peso"){
                textRange.setBackgroundResource(R.color.colorGreenish)
            }else if (textRange.text.toString() == "Normal"){
                textRange.setBackgroundResource(R.color.colorGreen)
            }else if (textRange.text.toString() == "Sobrepeso"){
                textRange.setBackgroundResource(R.color.colorYellow)
            }else if (textRange.text.toString() == "Obesidad grado 1"){
                textRange.setBackgroundResource(R.color.colorOrange)
            }else if (textRange.text.toString() == "Obesidad grado 2"){
                textRange.setBackgroundResource(R.color.colorBrown)
            }else if (textRange.text.toString() == "Obesidad grado 3"){
                textRange.setBackgroundResource(R.color.colorRed)
            }

        }

        peso.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                peso.text.clear()
            }
        }

        estatura.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                estatura.text.clear()
            }
        }

    }

    fun generarIMC (peso : Double, estatura : Double): Double {
        if (estatura <= 0){
            throw IllegalArgumentException("La estatura debe de ser mayor a 0");
        }
        if (peso <= 0){
            throw IllegalArgumentException("El peso debe de ser mayor a 0");
        }
        return peso / (estatura * estatura)
    }

    fun generarCategoria(imc : Double) : String {
        if (imc < 18.5){
            range = "Bajo peso"
        }else if (imc > 18.5 && imc <= 24.9){
            range = "Normal"
        }else if (imc > 25 && imc <= 29.9){
            range = "Sobrepeso"
        }else if (imc > 30 && imc <= 34.9){
            range = "Obesidad grado 1"
        }else if (imc > 35 && imc <= 39.9){
            range = "Obesidad grado 2"
        }else if (imc > 40){
            range = "Obesidad grado 3"
        }
        return range
    }

}