package com.maestrocorona.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondScreen(onBackPressed = { finish() })
        }
    }
}

@Composable
fun SecondScreen(onBackPressed: () -> Unit) {
    // Pantalla secundaria
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
            // Lista de restaurantes con fuente SansSerif
            Text(
                text = "Restaurante 1",
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Text(
                text = "Restaurante 2",
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )
            Text(
                text = "Restaurante 3",
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )

            // Botón para volver a la pantalla anterior
            Button(
                onClick = onBackPressed,
                modifier = Modifier.padding(top = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Volver",
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

// PREVIEW PARA VER LA SEGUNDA PANTALLA

@Preview(showBackground = true)
@Composable
fun PreviewSecondScreen() {
    SecondScreen(onBackPressed = {})
}
