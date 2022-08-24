package com.calorytracker.calorytracker.navigation

import androidx.navigation.NavController
import com.calorytracker.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}
