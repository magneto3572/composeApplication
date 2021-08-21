package com.example.composeapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.*
import com.example.composeapplication.ui.theme.Purple700
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen(this)
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        HomeScreen(mainActivity = MainActivity())
    }
}

@ExperimentalPagerApi
@Composable
fun HomeScreen(mainActivity: MainActivity) {
    val offset = remember { mutableStateOf(0f) }

    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        ){


        Column {
            headingtext(mainActivity)
            atmCard(mainActivity)
            Sample(mainActivity)
            bottomCardItem(mainActivity)
        }
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_cottage_black_24dp),
            BottomMenuContent("Bonus", R.drawable.ic_celebration_black_24dp),
            BottomMenuContent("Earning", R.drawable.ic_savings_black_24dp),
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = pink,
    activeTextColor: Color = white,
    inactiveTextColor: Color = grey,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(white)
            .padding(12.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor,
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = pink,
    activeTextColor: Color = Color.White,
    activeTextColor2: Color = pink,
    inactiveTextColor: Color = white,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Icon(
            painter = painterResource(id = item.iconId),
            contentDescription = item.title,
            tint = if (isSelected) activeTextColor2 else inactiveTextColor,
            modifier = Modifier.size(20.dp)
        )
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier
//                .clip(RoundedCornerShape(10.dp))
//                .background(if (isSelected) activeHighlightColor else Color.Transparent)
//                .padding(10.dp)
//        ) {
//
//        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor2 else inactiveTextColor
        )
    }
}


@Composable
fun headingtext(mainActivity: MainActivity){
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(10.dp),
            text ="Left To Spend",
            style = MaterialTheme.typography.body1
        )
        Text(
            text ="$ 12500.50",
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun atmCard(mainActivity: MainActivity){

    Card(modifier = Modifier
        .padding(20.dp, 10.dp, 20.dp, 20.dp)
        .clickable { test(mainActivity) }
        .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = pink,
        elevation = 10.dp
    ) {
        Box( modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally){

                Row(modifier = Modifier
                    .padding(0.dp, 50.dp, 0.dp, 16.dp)
                    .fillMaxWidth()
                    ,horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "**** **** **** ***",
                        style = MaterialTheme.typography.h2,
                        color = white
                    )

                    Image(modifier = Modifier
                        .rotate(180f)
                        .size(30.dp, 30.dp),
                        painter = painterResource(id = R.drawable.ic_wifi),
                        contentDescription = "",
                    )

                }

                Row(modifier = Modifier
                    .padding(0.dp, 15.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Column() {
                        Text(
                            text = "VALID THRU",
                            style= MaterialTheme.typography.body2,
                            color = white
                        )
                        Text(
                            text = "**/**",
                            color = white
                        )
                    }

                    Column() {
                        Text(
                            text = "CVV",
                            style= MaterialTheme.typography.body2,
                            color = white
                        )
                        Text(
                            text = "**/**",
                            color = white
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_icons8_mastercard_logo),
                        contentDescription = "",
                        contentScale = ContentScale.Inside
                    )
                }
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun Sample(mainActivity: MainActivity) {
    LazyRow(modifier = Modifier
        .padding(10.dp,0.dp,10.dp,0.dp),
        horizontalArrangement = Arrangement.Center){
        items(
            20
        ){
            Card(modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .clickable {
                    Toast
                        .makeText(mainActivity, "Item : $it", Toast.LENGTH_SHORT)
                        .show()
                }
                .size(320.dp, 130.dp)
                .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color.White,
                elevation = 10.dp
            ) {
                Row(modifier = Modifier
                    .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(modifier = Modifier
                        .size(40.dp, 40.dp),
                        painter = painterResource(id = R.drawable.gift), contentDescription ="" )
                    Column(modifier = Modifier
                        .padding(10.dp,0.dp,0.dp,0.dp),
                        verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "You Rock!",
                            style= MaterialTheme.typography.h6,
                            color = Color.Black
                        )
                        Text(modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp),
                            text = "Congratulation! You reached your monthly spending goal",
                            style = MaterialTheme.typography.body2,
                            color = Color.Black
                        )
                    }
                }
            }
        }   }
}

@Composable
fun bottomCardItem(mainActivity: MainActivity){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 0.dp),
    ) {
        Text(
            text = "Today",
            style = MaterialTheme.typography.h6,
            color = Color.Black,
            modifier= Modifier
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
        )
        LazyColumn(){
            items(
                10
            ){
                Card(modifier = Modifier
                    .padding(20.dp,15.dp,20.dp,0.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp, 20.dp, 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                                Column() {
                                    Image(modifier = Modifier
                                        .size(40.dp, 40.dp),
                                        painter = painterResource(id = R.drawable.coin), contentDescription ="" )
                                }
                                Column(modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)) {
                                    Text(modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp),
                                        text = "Starbucks",
                                        style = MaterialTheme.typography.body1,
                                        color = Color.Black
                                    )
                                    Text(modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp),
                                        text = "12:45",
                                        style = MaterialTheme.typography.body1,
                                        color = Color.Black
                                    )
                                }
                            }
                        }

                        Column() {
                            Text(modifier = Modifier.padding(0.dp,4.dp,0.dp,0.dp),
                                text = "$ 250",
                                style = MaterialTheme.typography.body1,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }

}

fun test(mainActivity: MainActivity) {
    Toast
        .makeText(
            mainActivity,
            "Card updated",
            Toast.LENGTH_SHORT).show()
}

