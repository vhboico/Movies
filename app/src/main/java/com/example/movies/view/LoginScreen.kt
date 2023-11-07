package com.example.movies.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.listener.Listener
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.gray_light
import com.example.movies.ui.theme.red
import com.example.movies.ui.theme.white
import com.example.movies.viewmodel.ViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.background(black),
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout {

                val (txtMovies, edtEmail, edtPassword, button, txtNew) = createRefs()

                Text(
                    text = "Movies",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = red,
                    modifier = Modifier.constrainAs(txtMovies) {
                        start.linkTo(parent.start, 20.dp)
                        top.linkTo(parent.top, 30.dp)
                    }
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(edtEmail) {
                            top.linkTo(txtMovies.bottom, 90.dp)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = gray_light,
                        textColor = black,
                        cursorColor = white,
                        focusedBorderColor = white,
                        unfocusedBorderColor = white,
                    ),
                    maxLines = 1,
                    label = {
                        Text(text = "Email", color = white)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(edtPassword) {
                            top.linkTo(edtEmail.bottom, 10.dp)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = gray_light,
                        textColor = black,
                        cursorColor = white,
                        focusedBorderColor = white,
                        unfocusedBorderColor = white,
                    ),
                    maxLines = 1,
                    label = {
                        Text(text = "Password", color = white)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                Button(
                    onClick = {
                        viewModel.login(email, password, object : Listener {
                            override fun onSuccess(message: String, screen: String) {
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                navController.navigate(screen)
                            }

                            override fun onFailure(error: String) {
                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                            }

                        })
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .height(50.dp)
                        .constrainAs(button) {
                            top.linkTo(edtPassword.bottom, 30.dp)
                        },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = red
                    )
                ) {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }

                TextButton(
                    onClick = {
                        navController.navigate("signUp")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(txtNew) {
                            top.linkTo(button.bottom, 10.dp)
                            start.linkTo(parent.start)
                        }

                ) {
                    Text(
                        text = stringResource(id = R.string.novo),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                    )
                }
            }
        }
    }
}
