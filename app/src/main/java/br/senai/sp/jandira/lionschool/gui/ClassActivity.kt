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
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClassActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ClassScreen()
                }
            }
        }
    }
}

@Composable
fun ClassScreen() {

    var student by remember {
        mutableStateOf(listOf<Student>())
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

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val call = RetrofitFactory().getCourseService().getStudentsByCourse()

            call.enqueue(object : Callback<StudentList> {
                override fun onResponse(
                    call: Call<StudentList>,
                    response: Response<StudentList>
                ) {
                    student = response.body()!!.Student
                }

                override fun onFailure(call: Call<StudentList>, t: Throwable) {
                    Log.i("DS2M", "onFailure:${t.message}")
                }
            })

            items(student) {
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(500.dp)
                        .padding(10.dp)
                ) {
                    Text(text = it.name, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(50.dp))
                    AsyncImage(
                        model = it.photo,
                        contentDescription = "avatar"
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        ClassScreen()
    }
}