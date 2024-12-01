package ir.dorantech.mvvmlarge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.dorantech.feature1.ui.screen.UserScreen
import ir.dorantech.mvvmlarge.ui.navigation.RouteApp
import ir.dorantech.mvvmlarge.ui.screen.MainScreen
import ir.dorantech.mvvmlarge.ui.theme.MvvmLargeScaleStructureTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmLargeScaleStructureTheme {
                Column {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = RouteApp.MainRoute
                    ) {
                        composable<RouteApp.MainRoute> {
                            MainScreen(onClickRetrofit = {navController.navigate(RouteApp.RetrofitRoute)})
                        }

                        composable<RouteApp.RetrofitRoute> {
                            UserScreen()
                        }
                    }
                }
            }
        }
    }
}