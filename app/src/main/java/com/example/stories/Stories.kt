package com.example.stories

import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

var FINAL_SLIDE = 7

val _drawables = mutableStateListOf<Any?>(
    R.drawable.stella_young,
    R.drawable.julian_crop,
    R.drawable.warren_crop,
    R.drawable.kenassa_crop,
    R.drawable.aj_crop,
    R.drawable.anna_crop,
    R.drawable.deej_crop,
    R.drawable.owen_crop
)
val drawables: List<Any?> = _drawables

val _slides = mutableStateListOf<List<Any?>>(
    listOf(
        R.string.stella_young,
        R.string.stella_subtitle,
        R.string.stella_description,
        R.string.stella_link,
        R.string.stella_url
    ),
    listOf(
        R.string.julian,
        R.string.julian_subtitle,
        R.string.julian_description,
        R.string.julian_link,
        R.string.julian_url
    ),
    listOf(
        R.string.warren,
        R.string.warren_subtitle,
        R.string.warren_description,
        R.string.warren_link,
        R.string.warren_url
    ),
    listOf(
        R.string.kenassa,
        R.string.kenassa_subtitle,
        R.string.kenassa_description,
        R.string.kenassa_link,
        R.string.kenassa_url
    ),
    listOf(
        R.string.AJ,
        R.string.AJ_subtitle,
        R.string.AJ_description,
        R.string.AJ_link,
        R.string.AJ_url
    ),
    listOf(
        R.string.anna,
        R.string.anna_subtitle,
        R.string.anna_description,
        R.string.anna_link,
        R.string.anna_url
    ),
    listOf(
        R.string.DJ,
        R.string.DJ_subtitle,
        R.string.DJ_description,
        R.string.DJ_link,
        R.string.DJ_url
    ),
    listOf(
        R.string.owen,
        R.string.owen_subtitle,
        R.string.owen_description,
        R.string.owen_link,
        R.string.owen_url
    )
)
val slides: List<List<Any?>> = _slides

@Composable
fun StoriesApp() {
    var page by rememberSaveable {mutableStateOf(0)}

    fun addSlide(words: List<String>) {
        _slides.add(words)
    }

    fun addDrawable(drawable: Any?) {
        _drawables.add(drawable)
    }

    when (page) {
        0 -> HomePage {page = it}
        1 -> StoriesContent(drawables, slides) {page = 0}
        2 -> AddStory(
            goHome = {page = 0},
            addStory = { title: String, sub: String, desc: String, link: String, url: String, image:Uri? ->
                if (title == "" || (link == "").xor(url == "") || !URLUtil.isValidUrl(url)) {
                    return@AddStory false
                }
                addSlide(listOf(title, sub, desc, link, url))
                addDrawable(image)
                FINAL_SLIDE++
                return@AddStory true
            }
        )
        3 -> LearnMore {page = 0}
        4 -> About {page = 0}

        else -> Box{}
    }
}

@Composable
fun HomePage(changePage: (Int) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.horizontalGradient(listOf(Color.Blue, Color.Cyan))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Stories", fontSize = 40.sp)
        Button(onClick = {changePage(1)} ) {
            Text(text = "Explore Stories", textAlign = TextAlign.Center)
        }
        Button(onClick = {changePage(2)} ) {
            Text(text = "Add Your Story", textAlign = TextAlign.Center)
        }
        Button(onClick = {changePage(3)} ) {
            Text(text = "Learn More", textAlign = TextAlign.Center)
        }
        Button(onClick = {changePage(4)} ) {
            Text(text = "About", textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun StoriesContent(drawables: List<Any?>, slides: List<List<Any?>>, returnHome: (Int) -> Unit) {
    var slide by rememberSaveable {mutableStateOf(0)}
    Box {
        val cur = drawables[slide]
        Image(
            painter = if (cur is Int) painterResource(cur) else rememberAsyncImagePainter(cur),
            contentDescription = stringResource(R.string.background_description),
            contentScale = ContentScale.Crop
        )
        Column {
            BasicSlide(
                slides[slide]
            )
            Footer(
                page = slide,
                increase = {
                    slide = it + 1
                    if (slide > FINAL_SLIDE) {
                        slide = 0
                    }
                },
                decrease = {
                    slide = it - 1
                    if (slide < 0) {
                        slide = FINAL_SLIDE
                    }
                },
                home = returnHome
            )
        }
    }
}

@Composable
fun AddStory(goHome: () -> Unit, addStory: (String, String, String, String, String, Uri?) -> Boolean) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Blue,
                            Color.Cyan
                        )
                    )
                )
                .fillMaxWidth()
                .fillMaxHeight(0.9F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Add your own story to the collection",
                modifier = Modifier.padding(top = 40.dp)
            )
            var title by remember { mutableStateOf("") }
            var subtitle by remember { mutableStateOf("") }
            var desc by remember { mutableStateOf("") }
            var linkText by remember { mutableStateOf("") }
            var url by remember { mutableStateOf("") }
            var image: Uri? by remember { mutableStateOf(null) }
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                label = { Text("Subtitle") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text("Description") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = linkText,
                onValueChange = { linkText = it },
                label = { Text("Link Text") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("URL") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            val picturePicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
                image = it
            }
            Button(
                onClick = {
                    picturePicker.launch("image/*")
                }
            ) {
                Text(text = image?.toString() ?: "Select a background image")
            }

            val confirmation = Toast.makeText(LocalContext.current, "Added your story!", Toast.LENGTH_SHORT)
            val failure = Toast.makeText(LocalContext.current, "Failed to add.", Toast.LENGTH_SHORT)

            Button(onClick = {
                if (addStory(title, subtitle, desc, linkText, url, image)) {
                    confirmation.show()
                    title = ""; subtitle = ""; desc = ""; linkText = ""; url = ""; image = null
                }
                else {
                    failure.show()
                }
            }) {
                Text(text = "Submit Story")
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = goHome) {
                Text(text = "Home")
            }
        }
    }
}

@Composable
fun About(goHome: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.Blue,
                            Color.Cyan
                        )
                    )
                )
                .fillMaxWidth()
                .fillMaxHeight(0.9F)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "About Stories", fontSize = 30.sp, color = Color.White, textAlign = TextAlign.Center, style = TextStyle(shadow = Shadow(
                color = Color.Black,
                blurRadius = 13F
            )))
            Text(text = "Stories is an app designed to learn about and share stories about disability.", color = Color.White, style = TextStyle(shadow = Shadow(
                color = Color.Black,
                blurRadius = 13F
            )))
            Text(text = "Stories believes that the best way to be educated about an issue is to learn about it from first-hand accounts. " +
                    "That is what Stories provides. With new individuals posting their stories each day, there is always new and informative " +
                    "stories to browse. Share your own experiences with the world!", textAlign = TextAlign.Justify, style = TextStyle(shadow = Shadow(
                color = Color.Black,
                blurRadius = 13F
            )),
            color = Color.White)
            Text(text = "This app was made at the University of Washington in Winter Quarter 2023 for EDSPE 304's What I Learned Project.", color = Color.White, style = TextStyle(shadow = Shadow(
                color = Color.Black,
                blurRadius = 13F
            )))
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = goHome) {
                Text(text = "Home")
            }
        }
    }
}

@Composable
fun LearnMore(goHome: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9F)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.Blue,
                        Color.Cyan
                    )
                )
            )
        ) {
            Text(text = "Work in Progress.")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = goHome) {
                Text(text = "Home")
            }
        }
    }
}

@Composable
fun BasicSlide(title: String, subtitle: String, description: String, linkText: String, url: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9F)
    ) {
        TitleAndSubtitle(title = title, sub = subtitle, modifier = modifier)
        DescriptionAndLink(
            desc = description,
            linkText = linkText,
            url = url,
            modifier = modifier
        )
    }
}

@Composable
fun BasicSlide(strings: List<Any?>, modifier: Modifier = Modifier) {
    if (strings[0] is String) {
        BasicSlide(strings[0] as String, strings[1] as String, strings[2] as String, strings[3] as String, strings[4] as String, modifier)
    }
    else {
        BasicSlide(stringResource(strings[0] as Int), stringResource(strings[1] as Int), stringResource(strings[2] as Int), stringResource(strings[3] as Int), stringResource(strings[4] as Int), modifier)
    }
}

@Composable
fun TitleAndSubtitle(title: String, sub: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, fontSize = 24.sp, color = Color.White, style = TextStyle(shadow = Shadow(
            color = Color.Black,
            blurRadius = 13F
        )))
        Spacer(Modifier.size(16.dp))
        Text(sub, color = Color.White, textAlign = TextAlign.Center, style = TextStyle(shadow = Shadow(
            color = Color.Black,
            blurRadius = 13F
        )))
    }
}

@Composable
fun DescriptionAndLink(desc: String, linkText: String, url: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    blurRadius = 13F
                ),
                fontSize = 20.sp,
                textAlign = TextAlign.Justify
            ),
            color = Color.White,
            text = desc
        )
        Spacer(modifier = Modifier.size(16.dp))
        LinkText(str = linkText, textAlign = TextAlign.Center, url = url)
    }
}

@Composable
fun LinkText(modifier: Modifier = Modifier, str: String, textAlign: TextAlign = TextAlign.Center, url: String) {
    val annotatedString = buildAnnotatedString {
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Cyan,
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.length
        )

        addStringAnnotation(
            tag = "URL",
            annotation = url,
            start = 0, end = str.length
        )
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        text = annotatedString,
        style = TextStyle(textAlign = textAlign),
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let{ stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }

    )
}

@Composable
fun Footer(page: Int, increase: (Int) -> Unit, decrease: (Int) -> Unit, home: (Int) -> Unit) {
    Row (
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationButton("Previous", page, decrease)
        NavigationButton("Home", page, home)
        NavigationButton("Next", page, increase)
    }
}

@Composable
fun NavigationButton(text: String, page: Int, onClick: (Int) -> Unit) {
    ClickableText(
        text = buildAnnotatedString { append(text) },
        style = TextStyle(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        ),
        onClick = {
            onClick(page)
        },
        modifier = Modifier.padding(horizontal = 30.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun StoriesPreview() {
    StoriesApp()
}