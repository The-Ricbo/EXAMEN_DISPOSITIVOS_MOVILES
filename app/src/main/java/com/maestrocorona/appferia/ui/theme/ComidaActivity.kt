
package com.maestrocorona.appferia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class ComidaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComidaScreen(onBack = { finish()})
        }
    }
}

@Composable
fun ComidaScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.comida),
            contentDescription = "Comida",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
            fontSize = 16.sp
        )
        Button(onClick = onBack) {
            Text("Regresar")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewComida() {
    ComidaScreen(onBack = {})
}
