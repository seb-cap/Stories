package com.example.stories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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

const val FINAL_SLIDE = 7

@Composable
fun StoriesApp() {
    var slide by rememberSaveable {mutableStateOf(0)}
    Box {
        Image(
            painter = when (slide) {
                0 -> painterResource(R.drawable.stella_young)
                1 -> painterResource(R.drawable.julian_crop)
                2 -> painterResource(R.drawable.warren_crop)
                3 -> painterResource(R.drawable.kenassa_crop)
                4 -> painterResource(R.drawable.aj_crop)
                5 -> painterResource(R.drawable.anna_crop)
                6 -> painterResource(R.drawable.deej_crop)
                7 -> painterResource(R.drawable.owen_crop)
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
                2 -> Warren()
                3 -> Kenassa()
                4 -> AJ()
                5 -> Anna()
                6 -> DEEJ()
                7 -> Owen()
                else -> Box {}
            }
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
                }
            )
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
fun StellaYoung() {
    BasicSlide(
        title = "Stella Young",
        subtitle = "I am not your inspiration, thank you very much",
        description = "Stella Young is a comedian and journalist who happens to go about her day in a wheelchair — a fact that doesn't, she'd like to make clear, automatically turn her into a noble inspiration to all humanity. In this very funny talk, Young breaks down society's habit of turning disabled people into \"inspiration porn.\"",
        linkText = "Tap here to listen.",
        url = "https://www.ted.com/talks/stella_young_i_m_not_your_inspiration_thank_you_very_much/transcript?language=en"
    )
}

@Composable
fun Julian() {
    BasicSlide(
        title = "Julian's Story",
        subtitle = "Pride in sign language",
        description = "Julian, an African American Caucasian boy who is deaf describes his experiences at the Washington School for the Deaf, and explains why people should learn to sign.",
        linkText = "Tap here to watch.",
        url = "https://www.youtube.com/watch?v=Iz3_n5g4LRs&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun Warren() {
    BasicSlide(
        title = "Warren's Story",
        subtitle = "Tools for school with ADHD",
        description = "Student with disability Warren Lybbert, a 9th grader at Quincy High School, describes in his own voice the challenges he faces and how assistive technology helps him.",
        linkText = "Tap here to watch.",
        url = "https://www.youtube.com/watch?v=PJ7jK_IyOPs&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun Kenassa() {
    BasicSlide(
        title = "Kenassa's Story",
        subtitle = "Expression with Autism",
        description = "Kenassa, a Japanese, Chinese and Oromo (East African) student with disabilities talks about his experiences at school, and explains how Autism is a way of understanding from a different perspective.",
        linkText = "Tap here to view.",
        url = "https://www.youtube.com/watch?v=ZUzupiG1D3Y&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun AJ() {
    BasicSlide(
        title = "AJ's Story",
        subtitle = "Wheeling towards inclusion",
        description = "Middle school student with disability Angelina (A.J.) Gammons-Reese, a 7th grade African American of Haitian decent at Meridian Parent Partnership Program,  describe in their own voice the challenges they face and their desire to be included.",
        linkText = "Tap here to watch.",
        url = "https://www.youtube.com/watch?v=1XQyuMHUwrc&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun Anna() {
    BasicSlide(
        title = "Anna's Story",
        subtitle = "Blindness is more of a social disability than a physical disability",
        description = "Anna, a 12th grader at Issaquah High School shares her experiences as a student with disabilities and her hopes for the future.",
        linkText = "Tap here to watch",
        url = "https://www.youtube.com/watch?v=8GrgEOsfl_M&ab_channel=OfficeoftheEducationOmbuds"
    )
}

@Composable
fun DEEJ() {
    BasicSlide(
        title = "DJ Savarese",
        subtitle = "Text-to-voice independence",
        description = "Abandoned by his birth parents and presumed incompetent, DJ Savarese (“Deej”) found not only a loving family but also a life in words, which he types on a text-to-voice synthesizer. As he makes his way through high school and dreams of college, he confronts the terrors of his past, society's obstacles to inclusion, and the sometimes paralyzing beauty of his own senses. In his advocacy on behalf of other nonspeaking autistics, he embraces filmmaking and poetry, and discovers what having a voice can truly mean.",
        linkText = "Tap here to learn more.",
        url = "https://www.deejmovie.com/"
    )
}

@Composable
fun Owen() {
    BasicSlide(
        title = "Owen Suskind",
        subtitle = "Expression via Disney with Autism",
        description = "By evocatively interweaving classic Disney sequences with verité scenes from Owen’s life, the film explores how identification and empathy with characters like Simba, Jafar, and Ariel create a context for him to understand his feelings and interpret reality. Beautiful, original animations further give form to Owen’s rich dialogue with all the Disney classics as he imagines himself heroically facing adversity in a tribe of sidekicks.",
        linkText = "Tap to watch the documentary",
        url = "https://www.youtube.com/watch?v=oVMcYkRHtcU&ab_channel=YouTubeMovies"
    )
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
    StoriesApp()
}