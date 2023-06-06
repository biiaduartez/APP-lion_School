package br.senai.sp.jandira.lionschool.gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoursesScreen()
                }
            }
        }
    }
}

@Composable
fun CoursesScreen() {

    var course by remember{
        mutableStateOf(listOf<Course>())
    }

    val context = LocalContext.current

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

                LazyColumn(    modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){

                    val call = RetrofitFactory().getCourseService().getCourse()

                    call.enqueue(object : Callback<CourseList>{
                        override fun onResponse(
                            call: Call<CourseList>,
                            response: Response<CourseList>
                        ) {
                            course = response.body()!!.course
                        }

                        override fun onFailure(call: Call<CourseList>, t: Throwable) {
                            Log.i("ds2m","onFailure: ${t.message}")
                        }
                    })
                    items(course){
                        Button(modifier = Modifier
                            .padding(15.dp)
                            .width(250.dp)
                            .height(70.dp),
                            shape = CircleShape,
                            onClick = {
                                val openNext = Intent(context, ClassActivity2::class.java)
                                context.startActivity(openNext)
                            }) {

                            Text(
                                text = it.sigla,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                    }
                }
            }
        }
    }}

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        CoursesScreen()
    }
}