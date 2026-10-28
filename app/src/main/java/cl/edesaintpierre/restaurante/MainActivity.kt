package cl.edesaintpierre.restaurante

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.doAfterTextChanged
import cl.edesaintpierre.restaurante.modelo.CuentaMesa
import cl.edesaintpierre.restaurante.modelo.ItemMenu
import cl.edesaintpierre.restaurante.modelo.ItemMesa
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val cuenta = CuentaMesa(1)
    private val itemMesaPastel = ItemMesa(ItemMenu("Pastel de Choclo", "12000"), 0)
    private val itemMesaCazuela = ItemMesa(ItemMenu("Cazuela", "10000"), 0)

    private val formatoPesos: NumberFormat =
        NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    private lateinit var etCantidadPastel: EditText
    private lateinit var etCantidadCazuela: EditText
    private lateinit var tvSubtotalPastel: TextView
    private lateinit var tvSubtotalCazuela: TextView
    private lateinit var swPropina: SwitchCompat
    private lateinit var tvComida: TextView
    private lateinit var tvPropina: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enlazarVistas()

        cuenta.agregarItem(itemMesaPastel)
        cuenta.agregarItem(itemMesaCazuela)
        cuenta.aceptaPropina = swPropina.isChecked

        configurarEventos()
        actualizarPantalla()
    }

    private fun enlazarVistas() {
        etCantidadPastel = findViewById(R.id.etCantidadPastel)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        tvSubtotalPastel = findViewById(R.id.tvSubtotalPastel)
        tvSubtotalCazuela = findViewById(R.id.tvSubtotalCazuela)
        swPropina = findViewById(R.id.swPropina)
        tvComida = findViewById(R.id.tvComida)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotal = findViewById(R.id.tvTotal)
    }

    private fun configurarEventos() {
        etCantidadPastel.doAfterTextChanged { texto ->
            itemMesaPastel.cantidad = texto.toString().toIntOrNull() ?: 0
            actualizarPantalla()
        }
        etCantidadCazuela.doAfterTextChanged { texto ->
            itemMesaCazuela.cantidad = texto.toString().toIntOrNull() ?: 0
            actualizarPantalla()
        }
        swPropina.setOnCheckedChangeListener { _, estaActivado ->
            cuenta.aceptaPropina = estaActivado
            actualizarPantalla()
        }
    }

    private fun actualizarPantalla() {
        tvSubtotalPastel.text = formatoPesos.format(itemMesaPastel.calcularSubtotal())
        tvSubtotalCazuela.text = formatoPesos.format(itemMesaCazuela.calcularSubtotal())
        tvComida.text = formatoPesos.format(cuenta.calcularTotalSinPropina())
        tvPropina.text = formatoPesos.format(cuenta.calcularPropina())
        tvTotal.text = formatoPesos.format(cuenta.calcularTotalConPropina())
    }
}