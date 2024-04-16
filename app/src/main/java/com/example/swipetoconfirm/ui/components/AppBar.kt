@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.swipetoconfirm.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.swipetoconfirm.ui.theme.AquaWhite
import com.olehsh.swipetoconfirm.R

@Composable
fun AppBar(modifier: Modifier = Modifier, onNavigationButtonClick: () -> Unit = {}) {
  LargeTopAppBar(
    modifier = modifier
      .shadow(elevation = 4.dp),
    colors = TopAppBarDefaults.largeTopAppBarColors(
      containerColor = AquaWhite,
      titleContentColor = Color.Black
    ),
    title = {
      Column {
        Text(
          text = "Confirmation",
          style = MaterialTheme.typography.headlineMedium,
          color = Color.Black
        )
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = stringResource(R.string.confirm_transaction),
          style = MaterialTheme.typography.bodyMedium,
          color = Color.Black,
          fontWeight = FontWeight.Light
        )
      }
    },
    navigationIcon = {
      IconButton(onClick = { onNavigationButtonClick.invoke() }) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = "Localized description"
        )
      }
    }
  )
}
