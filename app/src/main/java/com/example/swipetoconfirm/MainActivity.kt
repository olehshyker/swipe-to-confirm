package com.example.swipetoconfirm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.swipetoconfirm.model.AccountInfo
import com.example.swipetoconfirm.model.PaymentInfo
import com.example.swipetoconfirm.ui.MainViewModel
import com.example.swipetoconfirm.ui.components.AccountInfoCard
import com.example.swipetoconfirm.ui.components.AppBar
import com.example.swipetoconfirm.ui.components.PaymentDetails
import com.example.swipetoconfirm.ui.components.SwipeToConfirm
import com.example.swipetoconfirm.ui.theme.AppTheme
import com.example.swipetoconfirm.ui.theme.AquaWhite

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        val viewModel by viewModels<MainViewModel>()

        ConstraintLayout(
          modifier = Modifier
            .fillMaxSize()
            .background(color = AquaWhite)
        ) {
          val (appBar, slideToUnlock, paymentDetailsCard) = createRefs()
          val (paymentDetails) = createRefs()

          AppBar(
            modifier = Modifier.constrainAs(appBar) {
              top.linkTo(parent.top)
            },
            onNavigationButtonClick = {
              finish()
            }
          )

          AccountInfoCard(
            modifier = Modifier.constrainAs(paymentDetailsCard) {
              top.linkTo(appBar.bottom, margin = 32.dp)
              start.linkTo(parent.start, margin = 16.dp)
              end.linkTo(parent.end, margin = 16.dp)
              width = Dimension.fillToConstraints
            },
            accountInfo = AccountInfo(
              accountNumber = "123456789075",
              availableBalance = 9999.99,
              phoneNumber = "+123 456 78 99"
            )
          )

          PaymentDetails(
            modifier = Modifier.constrainAs(paymentDetails) {
              top.linkTo(paymentDetailsCard.bottom)
              start.linkTo(paymentDetailsCard.start, margin = 16.dp)
              end.linkTo(paymentDetailsCard.end, margin = 16.dp)
              width = Dimension.fillToConstraints
            },
            paymentInfo = PaymentInfo(itemPrice = 50.99, fee = 5.09, total = 56.08)
          )

          SwipeToConfirm(
            modifier = Modifier
              .constrainAs(slideToUnlock) {
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 32.dp)
                width = Dimension.fillToConstraints
              },
            confirmationState = viewModel.confirmationState.collectAsState().value,
            onConfirmationRequested = {
              viewModel.onConfirmRequested()
            }
          )
        }
      }
    }
  }
}
