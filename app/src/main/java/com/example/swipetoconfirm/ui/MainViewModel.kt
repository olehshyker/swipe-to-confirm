package com.example.swipetoconfirm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipetoconfirm.model.ConfirmationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

  private val _confirmationState = MutableStateFlow<ConfirmationState>(ConfirmationState.Initial)
  val confirmationState = _confirmationState.asStateFlow()

  fun onConfirmRequested() {
    viewModelScope.launch {
      _confirmationState.value = ConfirmationState.Loading
      delay(1000)
      _confirmationState.value = ConfirmationState.Done
    }
  }
}
