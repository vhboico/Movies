package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.view.DescriptionScreen
import com.example.movies.view.LoginScreen
import com.example.movies.view.MoviesScreen
import com.example.movies.view.SignUp
import com.example.movies.view.SplashScreen
import com.example.movies.viewmodel.ViewModel
import com.example.movies.viewmodel.ViewModelApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModel: ViewModel = hiltViewModel()
            val viewModelApi: ViewModelApi = hiltViewModel()

            NavHost(navController = navController, startDestination = "splashScreen") {
                composable("splashScreen") {
                    SplashScreen(navController)
                }
                composable("loginScreen") {
                    LoginScreen(navController, viewModel)
                }
                composable("signUp") {
                    SignUp(navController, viewModel)
                }
                composable("moviesScreen") {
                    MoviesScreen(
                        navController,
                        viewModelApi
                    )
                }
                composable("descriptionScreen"){
                    DescriptionScreen()
                }
            }
        }
    }
}