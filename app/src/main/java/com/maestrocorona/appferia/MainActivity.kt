package com.maestrocorona.appferia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

// COLORES DEL MODO OSCURO Y CLARO
private val Purple40 = Color(0xFF6650a4)
private val Purple80 = Color(0xFFD0BCFF)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Llamada a la pantalla principal
            MainScreen(onNavigateToSecondActivity = {
                startActivity(Intent(this, Activity2::class.java))
            })
        }
    }
}

@Composable
fun MainScreen(onNavigateToSecondActivity: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BusinessItem("Juegos", R.drawable.juegos)
            BusinessItem("Puestos de comida", R.drawable.comida)
            BusinessItem("Puestos de recuerdos", R.drawable.recuerdos)
            BusinessItem("Artistas", R.drawable.artistas) // modificaciones hechas para cada nombre y imagen

            Button(
                onClick = onNavigateToSecondActivity,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Fechas importantes",
                    fontFamily = FontFamily.SansSerif
                )
            }
        }

    }
}

@Composable
fun BusinessItem(texto: String, imageResId: Int) { //se le agrega esa funcion para que reciba imagenes
    // Card individual para cada negocio o atracción
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp), // Esto redondea las cards o los bordes
        colors = CardDefaults.cardColors(
            containerColor = Purple40 // COLOR DEL MODO CLARO PARA LA CARD
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen representativa
            Image(
                painter = painterResource(id = imageResId),// aca se modifico el logo predeterminado
                contentDescription = texto,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            )
            // Texto del nombre del negocio
            Text(
                text = texto,
                fontFamily = FontFamily.SansSerif, // CAMBIO DE LETRA A SANSERIF
                fontSize = 18.sp, //MODIFICACION PARA EL TAMAÑO DE LETRAS
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// LOS PREVIUW PARA VISUALIZAR LA APP SIN TENER QUE ABRIR LA MAQUINA VIRTUAL

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(onNavigateToSecondActivity = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewBusinessItem() {
    BusinessItem("Ejemplo Preview", R.drawable.logo_rest)
}
