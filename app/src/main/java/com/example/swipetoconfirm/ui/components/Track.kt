package com.example.swipetoconfirm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.SwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Track(
  swipeState: SwipeableState<SwipeValue>,
  swipeFraction: Float,
  enabled: Boolean,
  modifier: Modifier = Modifier,
  content: @Composable (BoxScope.() -> Unit)
) {
  val density = LocalDensity.current
  var fullWidth by remember { mutableIntStateOf(0) }

  val horizontalPadding = 10.dp

  val startOfTrackPx = 0f
  val endOfTrackPx = remember(fullWidth) {
    with(density) { fullWidth - (2 * horizontalPadding + Thumb.Size).toPx() }
  }

  val snapThreshold = 0.8f
  val thresholds = { from: SwipeValue, _: SwipeValue ->
    if (from == SwipeValue.Start) {
      FractionalThreshold(snapThreshold)
    } else {
      FractionalThreshold(1f - snapThreshold)
    }
  }

  val backgroundColor by remember(swipeFraction) {
    derivedStateOf { calculateTrackColor(swipeFraction) }
  }

  Box(
    modifier = modifier
      .onSizeChanged { fullWidth = it.width }
      .height(56.dp)
      .fillMaxWidth()
      .swipeable(
        enabled = enabled,
        state = swipeState,
        orientation = Orientation.Horizontal,
        anchors = mapOf(
          startOfTrackPx to SwipeValue.Start,
          endOfTrackPx to SwipeValue.End
        ),
        thresholds = thresholds,
        velocityThreshold = Track.VelocityThreshold
      )
      .background(
        color = backgroundColor,
        shape = RoundedCornerShape(percent = 50)
      )
      .padding(
        PaddingValues(
          horizontal = horizontalPadding,
          vertical = 8.dp
        )
      ),
    content = content,
    contentAlignment = Alignment.Center
  )
}

object Track {
  val VelocityThreshold = SwipeableDefaults.VelocityThreshold * 10
}
