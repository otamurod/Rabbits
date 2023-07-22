package com.otamurod.rabbits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.otamurod.rabbits.ui.theme.RabbitsTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabbitsTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val rabbit = viewModel.state.value.rabbit
                    val isLoading = viewModel.state.value.isLoading

                    rabbit?.let {
                        Image(painter = rememberImagePainter(
                            data = rabbit.imageUrl,
                            builder = { crossfade(true) }
                        ), contentDescription = "Rabbit: ${rabbit.name}")
                        
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = rabbit.name,
                        fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = rabbit.description)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = viewModel::getRandomRabbit,
                            modifier = Modifier.align(Alignment.End)
                        ){
                            Text(text = "Next Rabbit!")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        if (isLoading){
                            CircularProgressIndicator()
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
    RabbitsTheme {

    }
}