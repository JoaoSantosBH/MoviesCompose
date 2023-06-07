package com.brq.hellocompose.core.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun ShowToastMessage(string: String) {
    return Toast.makeText(LocalContext.current, string, Toast.LENGTH_SHORT).show()
}