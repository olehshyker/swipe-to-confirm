package com.example.swipetoconfirm.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.olehsh.swipetoconfirm.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageThumb(
  swipeState: SwipeableState<SwipeValue>,
  imageResId: Int = R.drawable.ic_rocket
) {
  val vector = ImageVector.vectorResource(id = imageResId)
  val painter = rememberVectorPainter(image = vector)

  val widthInPixels =
    with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() }

  val currentOffsetX = remember { Animatable(swipeState.offset.value) }

  LaunchedEffect(swipeState.progress) {
    when {
      swipeState.progress.from == SwipeValue.Start && swipeState.progress.fraction == 1f -> {
        currentOffsetX.snapTo(0f)
        currentOffsetX.animateTo(
          targetValue = swipeState.offset.value + 50,
          animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
          )
        )
      }

      swipeState.progress.from == SwipeValue.End && swipeState.progress.fraction == 1f -> {
        currentOffsetX.animateTo(
          targetValue = widthInPixels,
          animationSpec = tween(100, easing = LinearOutSlowInEasing)
        )
      }

      else -> {
        currentOffsetX.snapTo(swipeState.offset.value)
      }
    }
  }

  Canvas(
    modifier = Modifier
      .fillMaxSize()
      .offset()
  ) {
    val painterSize = painter.intrinsicSize.toDpSize()
    val xOffset = currentOffsetX.value - painterSize.width.value / 2
    val yOffset = -painterSize.height.value / 2

    translate(left = xOffset, top = yOffset) {
      with(painter) {
        draw(painter.intrinsicSize)
      }
    }
  }
}
