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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun SignUp(
    navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var visibility by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val icon = if (visibility) {
        painterResource(id = R.drawable.baseline_visibility_24)
    } else {
        painterResource(id = R.drawable.baseline_visibility_off_24)
    }

    Scaffold(
        modifier = Modifier.background(black),
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout {

                val (txtLets, txtSign, txt, edtName, edtEmail, edtPassword, button) = createRefs()

                Text(
                    text = stringResource(id = R.string.lets),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier.constrainAs(txtLets) {
                        top.linkTo(parent.bottom, 100.dp)
                        start.linkTo(parent.start, 20.dp)
                        end.linkTo(parent.end)
                    })

                Text(
                    text = stringResource(id = R.string.siga),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(txtSign) {
                            top.linkTo(txtLets.bottom, 10.dp)
                            start.linkTo(parent.start, 10.dp)

                        })

                Text(
                    text = stringResource(id = R.string.txt),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(txt) {
                            top.linkTo(txtSign.bottom)
                            start.linkTo(parent.start, 120.dp)

                        })

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(edtName) {
                            top.linkTo(txt.bottom, 40.dp)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = gray_light,
                        textColor = white,
                        cursorColor = white,
                        focusedBorderColor = white,
                        unfocusedBorderColor = white,
                    ),
                    maxLines = 1,
                    label = {
                        Text(text = "Nome", color = white)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(edtEmail) {
                            top.linkTo(edtName.bottom, 10.dp)
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = gray_light,
                        textColor = white,
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
                        textColor = white,
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
                    ),
                    trailingIcon = {
                        IconButton(onClick = { visibility = !visibility }) {
                            Icon(painter = icon, contentDescription = "", tint = white)
                        }
                    },
                    visualTransformation = if (visibility) VisualTransformation.None
                    else PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        viewModel.signUp(name, email, password, object : Listener {
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
                        text = stringResource(id = R.string.cadastrar),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                }
            }
        }
    }
}