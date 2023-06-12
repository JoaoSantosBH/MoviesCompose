package com.brq.hellocompose.core.util

import android.content.Context
import android.widget.Toast


fun showToastMessage(context:Context, string: String) {
    return Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}