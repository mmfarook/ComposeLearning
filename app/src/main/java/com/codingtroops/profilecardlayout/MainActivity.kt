package com.codingtroops.profilecardlayout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.codingtroops.profilecardlayout.ui.MyTheme
import com.codingtroops.profilecardlayout.ui.roundCornerShape_12

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(topPickList: List<TopPickForYou> = discoveryList) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn {
                items(topPickList) { topPickItem ->
                    DiscoveryCard(topPickForYou = topPickItem)
                }
            }

        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.Home,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 12.dp),
            )
        },
        title = { Text("Top Pick For You") }
    )
}

@Composable
fun DiscoveryCard(topPickForYou: TopPickForYou) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 8.dp,
        backgroundColor = Color.White,
        shape = roundCornerShape_12
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                if (topPickForYou.bgImage != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        DiscoveryHeader(topPickForYou.bgImage)
                    }
                }
                topPickForYou.subject?.let {
                    DiscoverySubject(topPickForYou.subject)

                }
                topPickForYou.title?.let {
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    DiscoveryText(
                        text = topPickForYou.title, fontSize = 16.sp,
                        FontWeight.Bold, "#141414"
                    )
                }

                val text =
                    if (!topPickForYou.teacherName.isNullOrEmpty() && !topPickForYou.groupName.isNullOrEmpty()) {
                        topPickForYou.teacherName + " | " + topPickForYou.groupName
                    } else if (topPickForYou.teacherName.isNullOrEmpty()) {
                        topPickForYou.teacherName
                    } else if (!topPickForYou.groupName.isNullOrEmpty()) {
                        topPickForYou.groupName
                    } else {
                        ""
                    }
                if (!text.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    DiscoveryText(
                        text = text, fontSize = 12.sp,
                        FontWeight.Normal, "#141414"
                    )
                }
                val date = topPickForYou.date
                if (!date.isNullOrEmpty() || !topPickForYou.memberList.isNullOrEmpty()) {
                    Row(
                        Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (!date.isNullOrEmpty()) {
                            DiscoveryText(
                                text = date, fontSize = 12.sp,
                                FontWeight.Normal, "#C8141414"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        topPickForYou.memberList?.let {
                            memberList(memberList = topPickForYou.memberList)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
        }

    }
}


@Composable
fun DiscoveryHeader(backgroundUrl: String?) {
    Image(
        painter = rememberImagePainter(
            data = backgroundUrl
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp),
        contentScale = ContentScale.FillBounds,
        contentDescription = null
    )
}

@Composable
fun DiscoveryType() {
    Spacer(modifier = Modifier.width(8.dp))
    RoundTextView(text = "Session", "#FF703D")
}

@Composable
fun DiscoverySubject(subject: Subject) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        subject.icon?.let { iconUrl ->
            Image(
                painter = rememberImagePainter(
                    data = iconUrl,
                ),
                modifier = Modifier.size(20.dp),
                contentDescription = null,
            )
        }
        DiscoveryType()
        Spacer(modifier = Modifier.width(8.dp))
        val subjectName = subject.name
        if (subjectName != null) {
            RoundTextView(subject.name, subject.color)
        }
    }

}

@Composable
fun RoundTextView(text: String, color: String?) {
    Box(
        modifier = Modifier
            .clip(roundCornerShape_12)
            .wrapContentSize()
            .background(parseColor(color ?: ""), shape = roundCornerShape_12),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .wrapContentSize()
        )
    }
}

@Composable
fun DiscoveryText(text: String, fontSize: TextUnit, fontWeight: FontWeight, color: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = parseColor(color),
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 24.dp)
    )
}

@Composable
fun memberList(memberList: ArrayList<User>) {
    LazyRow {
        var xPadding = 0.dp
        var zIndex = 0
        items(memberList) {
            if (zIndex > 0) {
                xPadding -= 6.dp
            }
            member(size = 20.dp, border = 2.dp, xOffset = xPadding, zIndex = zIndex, user = it)
            zIndex++
        }
    }
}

@Composable
fun member(size: Dp, border: Dp, xOffset: Dp, zIndex: Int, user: User) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = border,
            color = parseColor(user.borderColor)
        ),
        modifier = Modifier
            .size(size)
            .offset(x = xOffset, y = 0.dp)
            .zIndex(zIndex.toFloat()),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(
                data = user.profileUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },
            ),
            modifier = Modifier
                .size(size)
                .offset(),
            contentDescription = "Profile picture description",
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides (
                    if (onlineStatus)
                        1f else ContentAlpha.medium)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
            Text(
                text = if (onlineStatus)
                    "Active now"
                else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}