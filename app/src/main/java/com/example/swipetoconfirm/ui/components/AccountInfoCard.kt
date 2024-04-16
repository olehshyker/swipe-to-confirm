package com.example.swipetoconfirm.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.swipetoconfirm.model.AccountInfo
import com.example.swipetoconfirm.ui.theme.DarkBlue
import com.olehsh.swipetoconfirm.R

@Composable
fun AccountInfoCard(
  modifier: Modifier = Modifier,
  accountInfo: AccountInfo
) {
  Card(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 6.dp
    ),
    shape = RoundedCornerShape(36.dp),
    modifier = modifier
      .wrapContentHeight(),
    colors = CardDefaults.elevatedCardColors(containerColor = DarkBlue)
  ) {
    PaymentItemTitle(title = stringResource(R.string.transfer_from))
    PaymentItemValue(
      value = accountInfo.accountNumber,
      vectorIconResId = R.drawable.baseline_credit_card_24
    )

    PaymentItemTitle(title = stringResource(R.string.available_balance))
    PaymentItemValue(
      value = accountInfo.availableBalance.toString(),
      vectorIconResId = R.drawable.baseline_monetization_on_24
    )

    PaymentItemTitle(title = stringResource(R.string.phone_number))
    PaymentItemValue(
      value = accountInfo.phoneNumber,
      vectorIconResId = R.drawable.baseline_phone_android_24
    )
    Spacer(modifier = Modifier.height(24.dp))
  }
}

@Composable
fun PaymentItemTitle(
  modifier: Modifier = Modifier,
  title: String? = null,
  textColor: Color = Color.LightGray,
  textStyle: TextStyle = MaterialTheme.typography.labelMedium
) {
  Text(
    modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp),
    text = title.orEmpty(),
    color = textColor,
    style = textStyle
  )
}

@Composable
fun PaymentItemValue(
  modifier: Modifier = Modifier,
  value: String,
  vectorIconResId: Int,
  textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
  val vector = ImageVector.vectorResource(id = vectorIconResId)
  val painter = rememberVectorPainter(image = vector)

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(painter = painter, contentDescription = null, tint = Color.White)
    Text(
      modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
      text = value,
      color = Color.LightGray,
      style = textStyle
    )
  }
}
