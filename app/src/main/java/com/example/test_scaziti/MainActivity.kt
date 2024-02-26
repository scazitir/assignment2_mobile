package com.example.test_scaziti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_scaziti.ui.theme.Test_scazitiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test_scazitiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                    color = Color(0xBFE0E0E0)
                ) {
                    ArtDisplayer()
                }
            }
        }
    }
}

@Composable
fun ArtDisplayer() {
    var index by remember { mutableStateOf(1) }
    val maxIndex = 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        DisplayImage(
            imageId = when (index) {
                1 -> R.drawable.picture1
                2 -> R.drawable.picture2
                3 -> R.drawable.picture3
                else -> throw IllegalArgumentException("Invalid index: $index")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DisplayText(index)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    index = if (index > 1) {
                        index - 1
                    } else {
                        maxIndex
                    }
                },
                modifier = Modifier.padding(12.dp)
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    index = if (index < maxIndex) {
                        index + 1
                    } else {
                        1
                    }
                },
                modifier = Modifier.padding(12.dp)
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun DisplayImage(imageId: Int) {
    val painter = painterResource(id = imageId)

    Box(
        modifier = Modifier
            .size(400.dp)
            .fillMaxHeight()
            .padding(16.dp)
            .shadow(6.dp, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun DisplayText(index: Int) {
    val imageTitle = when (index) {
        1 -> R.string.title_first_image
        2 -> R.string.title_second_image
        3 -> R.string.title_third_image
        else -> throw IllegalArgumentException("Invalid index: $index")
    }

    val imageArtist = when (index) {
        1 -> R.string.first_image_artist
        2 -> R.string.second_image_artist
        3 -> R.string.third_image_artist
        else -> throw IllegalArgumentException("Invalid index: $index")
    }

    val artYear = when (index) {
        1 -> R.string.first_image_year
        2 -> R.string.second_image_year
        3 -> R.string.third_image_year
        else -> throw IllegalArgumentException("Invalid index: $index")
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row{
                Text(
                    text = stringResource(id = imageTitle),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            Row{
                Text(
                    text = stringResource(id = imageArtist),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )

                Text(
                    text = stringResource(id = artYear),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Test_scazitiTheme {
        ArtDisplayer();
    }
}
