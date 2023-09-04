package com.koshake.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.koshake.core_api.app.AppWithFacade
import com.koshake.core_api.navigator.NavGraphHandler
import com.koshake.feature_home_api.HomeNavGraphHandler
import com.koshake.koshake.core_ui.ui.theme.GameOfThronesComposeTheme
import com.koshake.main.ui.di.MainComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var homeNavGraphHandler: HomeNavGraphHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            EntryPoint(homeNavGraphHandler)
        }
    }
}

@Composable
fun EntryPoint(navGraphHandler: NavGraphHandler) {
    GameOfThronesComposeTheme(content = { MainScreen(navGraphHandler) })
}
