package com.food.foodpanda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.food.foodpanda.ui.navigation.Navigation
import com.food.foodpanda.ui.navigation.NavigationDrawer
import com.food.foodpanda.ui.theme.FoodpandaTheme
import com.food.foodpanda.ui.viewmodel.MainScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        setContent {
            FoodpandaTheme {
                NavigationDrawer()
                //Navigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodpandaTheme {
        //Greeting("Android")
        //Navigation()
        //NavigationDrawer()
    }
}