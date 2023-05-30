package br.senai.sp.jandira.lionschool.gui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Cursos
import br.senai.sp.jandira.lionschool.model.CursosList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var cursos by remember {
        mutableStateOf(listOf<Cursos>())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(248, 248, 248))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = " ",
                modifier = Modifier
                    .height(111.dp)
                    .width(88.dp)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.studants),
                contentDescription = " ",
                modifier = Modifier
                    .size(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .height(300.dp)
                    .width(450.dp),
                elevation = 15.dp,
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color(255, 255, 255)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.description_courses),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Default,
                        modifier = Modifier.padding(5.dp),

                        )
                }   
                


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val call = RetrofitFactory().getCursoService().getCursos()
                        call.enqueue(object : Callback<CursosList> {
                            override fun onResponse(
                                call: Call<CursosList>,
                                response: Response<CursosList>
                            ) {
                                cursos = response.body()!!.cursos

                            }

                            override fun onFailure(call: Call<CursosList>, t: Throwable) {
                                Log.i(
                                    "ds2m",
                                    "onFailure: ${t.message}"

                                )
                            }

                        })


                        items(cursos) {

                                Button(modifier = Modifier
                                    .padding(15.dp)
                                    .width(180.dp)
                                    .height(72.dp),
                                    onClick = { /*TODO*/ }) {
                                    Text(
                                        text = it.sigla,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                        AsyncImage(model = it.icone, contentDescription = "simbolo")


                        }

                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        HomeScreen()
    }
}