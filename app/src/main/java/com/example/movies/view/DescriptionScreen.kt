package com.example.movies.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movies.R
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DescriptionScreen() {

    Scaffold(
        modifier = Modifier.background(black),
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout {

                val (img, txt1, img2, add, button, info, txt2, description, casting) = createRefs()

                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .constrainAs(img) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = "Curta dos minions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = white,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(txt1) {
                            top.linkTo(img.bottom, 15.dp)
                            start.linkTo(parent.start, 125.dp)
                        }
                )

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    modifier = Modifier.constrainAs(img2) {
                        top.linkTo(txt1.bottom, 15.dp)
                        start.linkTo(parent.start, 50.dp)
                    },
                    tint = white
                )

                Text(
                    text = "Minha lista",
                    fontSize = 16.sp,
                    color = white,
                    modifier = Modifier.constrainAs(add) {
                        top.linkTo(img2.bottom)
                        start.linkTo(parent.start, 22.dp)
                    }
                )

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = white
                    ),
                    modifier = Modifier.constrainAs(button) {
                        start.linkTo(add.end, 25.dp)
                        top.linkTo(txt1.bottom, 15.dp)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = ""
                    )

                    Text(
                        text = "Assistir",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = black
                    )
                }

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "",
                    tint = white,
                    modifier = Modifier.constrainAs(info) {
                        start.linkTo(button.end, 60.dp)
                        top.linkTo(txt1.bottom, 15.dp)
                    })

                Text(
                    text = "Saiba mais",
                    fontSize = 16.sp,
                    color = white,
                    modifier = Modifier.constrainAs(txt2) {
                        start.linkTo(button.end, 30.dp)
                        top.linkTo(info.bottom)
                    }
                )

                Text(text = "Descrição: Esta colêtanea de curtas da franquia meu Malvado favorito inclui Rodas de treino, filhotinhos amarelos e o novo preto",
                    color = white,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .constrainAs(description) {
                            start.linkTo(parent.start)
                            top.linkTo(button.bottom, 20.dp)
                        })

                Text(text = "Elenco: Pierre Coffin, Miranda Cosgrove, Dana Gaier",
                    fontSize = 16.sp,
                    color = white,
                    modifier = Modifier.padding(20.dp)
                        .constrainAs(casting){
                            start.linkTo(parent.start)
                            top.linkTo(description.bottom)
                        }

                )
            }
        }
    }
}

@Preview
@Composable
fun DescriptionPreview() {
    DescriptionScreen()
}