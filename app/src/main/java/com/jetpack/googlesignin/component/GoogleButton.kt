package com.jetpack.googlesignin.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.common.api.GoogleApi
import com.jetpack.googlesignin.R
import com.jetpack.googlesignin.ui.theme.Shapes

@Composable
fun GoogleButton(
    modifier: Modifier=Modifier,
    loadingState:Boolean=false,
    primaryText:String="Signin with Google",
    secondaryText:String="Please wait...",
    icon:Int= R.drawable.google_logo,
    shape:Shape= Shapes.medium,
    borderColor:Color=Color.LightGray,
    backgroundColor: Color=MaterialTheme.colors.surface,
    progressIndicatorColor:Color=Color.Blue,
    onClick:()->Unit
) {
    var buttonText by remember {
        mutableStateOf(primaryText)
    }
    LaunchedEffect(key1 = loadingState){
        buttonText=if (loadingState) secondaryText else primaryText
    }
    androidx.compose.material.Surface(modifier = modifier.
    clickable(enabled = !loadingState) {
        onClick()
    },
    shape=shape,
    border= BorderStroke(width = 1.dp, color = borderColor),
    color = backgroundColor
    ) {
Row(modifier= Modifier
    .padding(
        start = 12.dp, end = 16.dp,
        top = 12.dp, bottom = 12.dp
    )
    .animateContentSize(
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    ),
verticalAlignment = Alignment.CenterVertically,
horizontalArrangement = Arrangement.Center){
Icon(painter = painterResource(id = R.drawable.google_logo), contentDescription = "Google Singin"
, tint = Color.Unspecified, modifier = Modifier.size(30.dp))
    Spacer(modifier = Modifier.width(8.dp))
    Text(buttonText)
    if (loadingState){
        Spacer(modifier = Modifier.width(16.dp))
        CircularProgressIndicator(modifier=Modifier.size(
            16.dp
        ), strokeWidth = 2.dp,
        color = progressIndicatorColor)
    }
}
    }
}

@Preview
@Composable
fun GoogleButtonPreview(){
    GoogleButton() {

    }
}