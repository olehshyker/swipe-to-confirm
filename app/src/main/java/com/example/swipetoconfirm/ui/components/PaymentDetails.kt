@file:JvmName("PaymentDetailsKt")

package com.example.swipetoconfirm.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.swipetoconfirm.model.PaymentInfo
import com.olehsh.swipetoconfirm.R

@Composable
fun PaymentDetails(modifier: Modifier = Modifier, paymentInfo: PaymentInfo) {
  Column(modifier = modifier) {
    PaymentDetailsItem(
      title = stringResource(id = R.string.price),
      value = "$${paymentInfo.itemPrice}"
    )
    PaymentDetailsItem(title = stringResource(id = R.string.fee), value = "$${paymentInfo.fee}")
    PaymentDetailsItem(
      title = stringResource(id = R.string.total),
      value = "$${paymentInfo.total}"
    )
  }
}

@Composable
fun PaymentDetailsItem(modifier: Modifier = Modifier, title: String, value: String) {
  Row(
    modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)
  ) {
    Text(
      text = title,
      color = Color.Gray,
      style = MaterialTheme.typography.bodyLarge
    )
    Text(
      modifier = Modifier.weight(1f),
      text = value,
      textAlign = TextAlign.End,
      color = Color.Gray,
      style = MaterialTheme.typography.titleLarge
    )
  }
}
