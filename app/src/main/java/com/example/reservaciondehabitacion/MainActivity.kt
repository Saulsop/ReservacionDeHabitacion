package com.example.reservaciondehabitacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.reservaciondehabitacion.ui.theme.ReservacionDeHabitacionTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReservacionDeHabitacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // Variables para guardar los datos
    var fechaEntrada by remember { mutableStateOf(Date()) }
    var fechaSalida by remember { mutableStateOf(Date()) }
    var numeroPersonas by remember { mutableStateOf("") }

    // Formato para mostrar las fechas
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    // Estados para controlar la visibilidad de los calendarios
    var mostrarCalendarioEntrada by remember { mutableStateOf(false) }
    var mostrarCalendarioSalida by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de fecha de entrada
        Text(text = "Fecha de entrada")
        OutlinedTextField(
            value = formatoFecha.format(fechaEntrada),
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { mostrarCalendarioEntrada = true }) {
                    Icon(Icons.Default.DateRange, "Seleccionar fecha")
                }
            }
        )

        // DatePicker para fecha de entrada
        if (mostrarCalendarioEntrada) {
            DatePickerDialog(
                onDismissRequest = { mostrarCalendarioEntrada = false },
                confirmButton = {
                    TextButton(onClick = { mostrarCalendarioEntrada = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarCalendarioEntrada = false }) {
                        Text("Cancelar")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = fechaEntrada.time)
                DatePicker(
                    state = datePickerState,
                )
                // Actualizar la fecha cuando se confirma
                datePickerState.selectedDateMillis?.let { millis ->
                    fechaEntrada = Date(millis)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de fecha de salida
        Text(text = "Fecha de Salida")
        OutlinedTextField(
            value = formatoFecha.format(fechaSalida),
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { mostrarCalendarioSalida = true }) {
                    Icon(Icons.Default.DateRange, "Seleccionar fecha")
                }
            }
        )

        // DatePicker para fecha de salida
        if (mostrarCalendarioSalida) {
            DatePickerDialog(
                onDismissRequest = { mostrarCalendarioSalida = false },
                confirmButton = {
                    TextButton(onClick = { mostrarCalendarioSalida = false }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { mostrarCalendarioSalida = false }) {
                        Text("Cancelar")
                    }
                }
            ) {
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = fechaSalida.time)
                DatePicker(
                    state = datePickerState,
                )
                // Actualizar la fecha cuando se confirma
                datePickerState.selectedDateMillis?.let { millis ->
                    fechaSalida = Date(millis)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de número de personas
        Text(text = "Numero de personas")
        OutlinedTextField(
            value = numeroPersonas,
            onValueChange = { numeroPersonas = it },
            placeholder = { Text("Ingrese número") }
        )

        Button(onClick = {/*TODO*/ } ) {
            Text(text = "Reservar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReservacionDeHabitacionTheme {
        Greeting("Android")
    }
}