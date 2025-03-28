package ir.iact.starwarsplanets.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ir.iact.starwarsplanets.ui.theme.StarWarsPlanetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarWarsPlanetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlanetListScreen(
                        modifier = Modifier.padding(innerPadding),
                        planets = emptyList()

                    )
                }
            }
        }
    }
}