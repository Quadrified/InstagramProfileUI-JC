package com.quadrified.instagramprofileui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp)
    ) {
        TopBar(name = "quadrified")
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        ProfileDescription(
            displayName = "Omer Quadri",
            description = "Software Engineer\n" + "Compiling Magic\n" + "\"It's a small world, what else you gonna do\"",
            url = "github.com/quadrified",
            followedBy = listOf("saniiyayy, quadri, saif"),
            otherCount = 14
        )
        Spacer(modifier = Modifier.height(25.dp))
        ActionButtonsSection(modifier = modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(25.dp))
        StoryHighlights(
            highlights = listOf(
                ImageWithText(text = "Discord", image = painterResource(id = R.drawable.discord)),
                ImageWithText(text = "QnA", image = painterResource(id = R.drawable.qa)),
                ImageWithText(text = "Discord", image = painterResource(id = R.drawable.discord)),
                ImageWithText(text = "QnA", image = painterResource(id = R.drawable.qa)),
                ImageWithText(
                    text = "Telegram", image = painterResource(id = R.drawable.telegram)
                ),
                ImageWithText(text = "YouTube", image = painterResource(id = R.drawable.youtube))
            ), modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostsTabView(
            modifier = modifier, imageWithText = listOf(
                ImageWithText(image = painterResource(id = R.drawable.ic_grid), text = "Posts"),
                ImageWithText(image = painterResource(id = R.drawable.ic_reels), text = "Reels"),
                ImageWithText(image = painterResource(id = R.drawable.ic_igtv), text = "IgTV"),
                ImageWithText(image = painterResource(id = R.drawable.profile), text = "Tagged"),
            )
        ) {
            selectedTabIndex = it
            when (selectedTabIndex) {
                0 -> PostsGrid(
                    posts = listOf(
                        painterResource(id = R.drawable.intermediate_dev),
                        painterResource(id = R.drawable.bad_habits),
                        painterResource(id = R.drawable.kmm),
                        painterResource(id = R.drawable.learn_coding_fast),
                        painterResource(id = R.drawable.master_logical_thinking),
                        painterResource(id = R.drawable.multiple_languages),
                    ), modifier = modifier.fillMaxWidth()
                )
            }
        }
    }

}

@Composable
fun TopBar(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(26.dp)
            )
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = modifier.padding(start = 10.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painterResource(id = R.drawable.ic_bell),
                contentDescription = "Notifications",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painterResource(id = R.drawable.ic_dotmenu),
                contentDescription = "Notifications",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }


    }

}

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            ProfileImage(
                image = painterResource(id = R.drawable.omer),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(30.dp))
            ProfileStats(modifier = modifier.weight(7f))
        }
    }
}

@Composable
fun ProfileImage(image: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = image,
        contentDescription = "Profile Image",
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .padding(3.dp)
            .clip(shape = CircleShape)
    )

}

@Composable
fun ProfileStats(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ProfileStat(name = "posts", statNumber = "10")
        ProfileStat(name = "followers", statNumber = "54")
        ProfileStat(name = "following", statNumber = "1,268")
    }
}

@Composable
fun ProfileStat(name: String, statNumber: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = statNumber, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = name, fontSize = 12.sp)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followedBy: List<String>,
    otherCount: Int,
    modifier: Modifier = Modifier
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 24.sp

    Column {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            fontSize = 16.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = url,
            fontSize = 16.sp,
            color = Color(0xFF3D3D91),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    color = Color.Black, fontWeight = FontWeight.Bold
                )
                append("Followed by ")
                followedBy.forEachIndexed() { index, name ->
                    pushStyle(boldStyle)
                    append(name)
                    pop()
                    if (index < followedBy.size - 1) {
                        append(", ")
                    }
                }
                if (otherCount > 2) {
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$otherCount others")
                }
            }, letterSpacing = letterSpacing, lineHeight = lineHeight)
        }
    }

}

@Composable
fun ActionButtonsSection(modifier: Modifier = Modifier) {
    val minWidth = 100.dp
    val height = 30.dp

    Row(
        modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height = height)
        )
        ActionButton(
            text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height = height)
        )
        ActionButton(
            text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height = height)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = 10.dp)
                .height(height = height)
        )

    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String? = null, icon: ImageVector? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .border(
                width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp),
    ) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun StoryHighlights(modifier: Modifier = Modifier, highlights: List<ImageWithText>) {
    LazyRow(modifier = modifier) {
        items(highlights.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                ProfileImage(
                    image = highlights[it].image, modifier = modifier.size(70.dp)
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun PostsTabView(
    modifier: Modifier = Modifier,
    imageWithText: List<ImageWithText>,
    onTabSelected: @Composable (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        imageWithText.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
//                    onTabSelected(index)
                }) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                )
            }
        }

    }
}

@Composable
fun PostsGrid(posts: List<Painter>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.scale(1.01f)) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(width = 1.dp, color = Color.White)
                
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}