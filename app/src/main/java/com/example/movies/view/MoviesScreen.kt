package com.example.movies.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.itemlist.CategoryItemList
import com.example.movies.listener.ListenerListaFilmes
import com.example.movies.model.Categoria
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.dark_gray
import com.example.movies.ui.theme.red
import com.example.movies.ui.theme.white
import com.example.movies.viewmodel.ViewModel
import com.example.movies.viewmodel.ViewModelApi
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState",
    "CoroutineCreationDuringComposition"
)
@Composable
fun MoviesScreen(
    navController: NavController,
    viewModelApi: ViewModelApi = hiltViewModel(),
    viewModel: ViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val name = viewModel.userProfile().collectAsState("").value

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
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier
                    )

                    TextButton(onClick = {
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.setTitle("Do you really want to leave?")
                        alertDialog.setMessage("If you log out, you will have to log in again")
                        alertDialog.setPositiveButton("Yes") { _, _ ->
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("loginScreen")

                        }
                        alertDialog.setNegativeButton("No") { _, _ -> }
                            .show()
                    }
                    ) {
                        Text(
                            text = "Out",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = white
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(dark_gray)

        ) {

            var listaDeCategorias by remember {
                mutableStateOf<MutableList<Categoria>>(mutableListOf())
            }

            scope.launch(Dispatchers.IO) {
                viewModelApi.getFilmes(object : ListenerListaFilmes {
                    override fun onResponse(listaCategoria: MutableList<Categoria>) {
                        listaDeCategorias = listaCategoria
                    }

                    override fun onFailure(error: String) {
                        TODO("Not yet implemented")
                    }

                })
            }

            LazyColumn {
                itemsIndexed(listaDeCategorias) { position, _ ->
                    CategoryItemList(position = position, listaCategoria = listaDeCategorias, navController)
                }
            }
        }
    }
}