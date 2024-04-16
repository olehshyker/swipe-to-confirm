@file:OptIn(ExperimentalMaterialApi::class)

package com.example.swipetoconfirm.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeProgress
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swipetoconfirm.model.ConfirmationState
import com.example.swipetoconfirm.ui.theme.DarkBlue
import com.example.swipetoconfirm.ui.theme.LightGreen
import com.olehsh.swipetoconfirm.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToConfirm(
  confirmationState: ConfirmationState,
  onConfirmationRequested: () -> Unit,
  modifier: Modifier = Modifier
) {
  val hapticFeedback = LocalHapticFeedback.current

  val isLoading = confirmationState is ConfirmationState.Loading

  val swipeState = rememberSwipeableState(
    initialValue = if (isLoading) SwipeValue.End else SwipeValue.Start,
    confirmStateChange = { anchor ->
      if (anchor == SwipeValue.End) {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        onConfirmationRequested()
      }
      true
    }
  )

  val swipeFraction by remember {
    derivedStateOf { calculateSwipeFraction(swipeState.progress) }
  }

  LaunchedEffect(confirmationState) {
    when (confirmationState) {
      ConfirmationState.Initial -> swipeState.animateTo(SwipeValue.Start)
      ConfirmationState.Loading -> swipeState.animateTo(SwipeValue.End)
      ConfirmationState.Done -> {}
    }
  }

  Track(
    swipeState = swipeState,
    swipeFraction = swipeFraction,
    enabled = !isLoading && confirmationState != ConfirmationState.Done,
    modifier = modifier
  ) {
    Hint(
      text = stringResource(R.string.swipe_to_confirm),
      confirmationState = confirmationState,
      swipeFraction = swipeFraction,
      modifier = Modifier
        .align(Alignment.Center)
        .padding(PaddingValues(horizontal = Thumb.Size + 8.dp))
    )
    ImageThumb(
      swipeState = swipeState
    )
  }
}

fun calculateSwipeFraction(progress: SwipeProgress<SwipeValue>): Float {
  val atAnchor = progress.from == progress.to
  val fromStart = progress.from == SwipeValue.Start
  return if (atAnchor) {
    if (fromStart) 0f else 1f
  } else {
    if (fromStart) progress.fraction else 1f - progress.fraction
  }
}

fun calculateTrackColor(swipeFraction: Float): Color {
  val fraction = (swipeFraction).coerceIn(0f..1f)
  return lerp(DarkBlue, LightGreen, fraction)
}

@Composable
fun Hint(
  text: String,
  swipeFraction: Float,
  modifier: Modifier = Modifier,
  confirmationState: ConfirmationState
) {
  val hintTextColor by remember(swipeFraction) {
    derivedStateOf { calculateHintTextColor(swipeFraction) }
  }

  AnimatedVisibility(
    visible = confirmationState == ConfirmationState.Loading
  ) {
    CircularProgressIndicator(
      modifier = Modifier.padding(2.dp),
      color = Color.White,
      strokeWidth = 2.dp
    )
  }

  AnimatedVisibility(
    visible = confirmationState == ConfirmationState.Done,
    enter = fadeIn(),
    exit = fadeOut()
  ) {
    Image(
      imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_mark),
      contentDescription = null
    )
  }

  if (confirmationState == ConfirmationState.Initial) {
    Text(
      text = text,
      color = hintTextColor,
      style = MaterialTheme.typography.titleSmall,
      modifier = modifier
    )
  }
}

fun calculateHintTextColor(swipeFraction: Float): Color {
  val endOfFadeFraction = 0.35f
  val fraction = (swipeFraction / endOfFadeFraction).coerceIn(0f..1f)
  return lerp(Color.White, Color.White.copy(alpha = 0f), fraction)
}

object Thumb {
  val Size = 70.dp
}

@Preview
@Composable
private fun Preview() {
  val previewBackgroundColor = Color(0xFFEDEDED)
  var isLoading by remember { mutableStateOf(false) }

  val confirmationState = ConfirmationState.Loading
  MaterialTheme {
    Column(
      verticalArrangement = spacedBy(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .background(previewBackgroundColor)
        .padding(horizontal = 24.dp)
    ) {
      SwipeToConfirm(
        confirmationState = confirmationState,
        onConfirmationRequested = { isLoading = true }
      )
    }
  }
}
