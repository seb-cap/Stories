package com.example.stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StoriesApp(
    modifier: Modifier = Modifier
) {
    var slide by remember {mutableStateOf(0)}
    Box {
        Image(
            painter = when (slide) {
                0 -> painterResource(R.drawable.stella_young)
                1 -> painterResource(R.drawable.julian_crop)
                else -> painterResource(R.drawable.ic_launcher_background)
            },
            contentDescription = "Image of speaker",
            contentScale = ContentScale.Crop,
            modifier = Modifier.blur(
                radiusX = 5.dp,
                radiusY = 5.dp
            )
        )
        Column {
            when (slide) {
                0 -> StellaYoung()
                1 -> Julian()
                else -> Box {}
            }
            Footer(slide, {slide = it + 1}, {slide = it - 1})
        }
    }
}

@Composable
fun BasicSlide(title: String, subtitle: String, description: String, linkText: String, url: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(0.9F)
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
fun StellaYoung(modifier: Modifier = Modifier) {
    BasicSlide(
        title = "Stella Young",
        subtitle = "I am not your inspiration, thank you very much",
        description = "Stella Young is a comedian and journalist who happens to go about her day in a wheelchair — a fact that doesn't, she'd like to make clear, automatically turn her into a noble inspiration to all humanity. In this very funny talk, Young breaks down society's habit of turning disabled people into \"inspiration porn.\"",
        linkText = "Tap here to listen.",
        url = "https://www.ted.com/talks/stella_young_i_m_not_your_inspiration_thank_you_very_much/transcript?language=en"
    )
}

@Composable
fun Julian(modifier: Modifier = Modifier) {
    BasicSlide(
        title = "Julian's Story",
        subtitle = "Pride in signing",
        description = "Julian, an African American Caucasian boy who is deaf describes his experiences at the Washington School for the Deaf, and explains why people should learn to sign.",
        linkText = "Tap here to watch.",
        url = "https://www.youtube.com/watch?v=Iz3_n5g4LRs&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun TitleAndSubtitle(title: String, sub: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, fontSize = 24.sp, color = Color.White)
        Spacer(Modifier.size(16.dp))
        Text(sub, color = Color.White)
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
        modifier = modifier.padding(16.dp).fillMaxWidth(),
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
fun Footer(page: Int, increase: (Int) -> Unit, decrease: (Int) -> Unit) {
    Row (
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationButton("Previous", page, decrease)
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
    StoriesApp(Modifier.fillMaxSize())
}