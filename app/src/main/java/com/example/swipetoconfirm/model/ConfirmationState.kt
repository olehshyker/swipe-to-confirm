package com.example.swipetoconfirm.model

sealed class ConfirmationState {
  data object Initial : ConfirmationState()
  data object Loading : ConfirmationState()
  data object Done : ConfirmationState()
}
