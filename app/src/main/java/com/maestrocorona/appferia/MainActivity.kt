package com.maestrocorona.appferia

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font as GoogleFontInstance
import androidx.compose.ui.text.googlefonts.GoogleFont.Provider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontFamily as ComposeFontFamily




// ✅ COLOR PRINCIPAL
private val Purple40 = Color(0xFF6650a4)

// ✅ FUENTE PERSONALIZADA: POPPINS
val provider = Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val poppinsFont = GoogleFont("Poppins")
val modernFontFamily = ComposeFontFamily(GoogleFontInstance(poppinsFont, provider))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(onNavigateToSecondActivity = {
                startActivity(Intent(this, Activity2::class.java))
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onNavigateToSecondActivity: () -> Unit) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var showWebView by remember { mutableStateOf(false) }

    if (showWebView) {
        Column {
            //  Botón para volver a la pantalla principal
            Button(
                onClick = { showWebView = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("← Volver")
            }

            //  WebView cargando la página
            WebViewScreen(url = "https://thecatapi.com/")
        }

} else {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menú de opciones",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontFamily = modernFontFamily
                )
                Spacer(modifier = Modifier.height(8.dp))

                BusinessItem("Juegos", R.drawable.juegos)
                BusinessItem("Puestos de comida", R.drawable.comida)
                BusinessItem("Puestos de recuerdos", R.drawable.recuerdos)
                ClickableCard("Artistas", R.drawable.artistas) {
                    context.startActivity(Intent(context, CarteleraActivity::class.java))
                }
                ClickableCard("The Cat API", R.drawable.cat) {
                    showWebView = true
                    coroutineScope.launch { drawerState.close() }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Regresar al inicio", fontFamily = modernFontFamily)
                }
            }
            }

            ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Feria 2024", fontFamily = modernFontFamily) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            },
            content = { paddingValues ->
                // ✅ NUEVO DISEÑO DE LA PANTALLA PRINCIPAL
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(80.dp))


                    // ✅ IMAGEN CENTRADA
                    Image(
                        painter = painterResource(id = R.drawable.feria2024),
                        contentDescription = "Imagen Feria 2024",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )

                    Spacer(modifier = Modifier.height(80.dp))


                    // ✅ BOTÓN ABAJO
                    Button(
                        onClick = onNavigateToSecondActivity
                    ) {
                        Text(
                            text = "Fechas importantes",
                            fontFamily = modernFontFamily
                        )
                    }
                }
            }
        )
    }
    }
}



// ✅ CARD DECORATIVA (NO CLICKEABLE)
@Composable
fun BusinessItem(texto: String, imageResId: Int) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.97f else 1f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(vertical = 8.dp)
            .scale(scale),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEDE7F6)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = texto,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp)
            )
            Text(
                text = texto,
                fontFamily = modernFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A148C),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ✅ CARD CLICKEABLE (CARTELERA)
@Composable
fun ClickableCard(texto: String, imageResId: Int, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.97f else 1f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(vertical = 8.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE1F5FE)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = texto,
                modifier = Modifier
                        .size(80.dp)
                    .padding(end = 12.dp)
            )
            Text(
                text = texto,
                fontFamily = modernFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF01579B),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(onNavigateToSecondActivity = {})
}