package com.example.movies.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.red
import com.example.movies.viewmodel.ViewModel
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = red
                    )
                },
                backgroundColor = black,
                actions = {

                    Text(
                        text = "John",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = red,
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    TextButton(onClick = {
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.setTitle("Deseja realmente sair?")
                        alertDialog.setMessage("Se sair, terá que logar novamente")
                        alertDialog.setPositiveButton("Sim") { _, _ ->
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("loginScreen")

                        }
                        alertDialog.setNegativeButton("Não") { _, _ -> }
                            .show()
                    }
                    ) {

                        Text(
                            text = "Sair",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = red
                        )
                    }
                }
            )
        }
    ) {

    }

}