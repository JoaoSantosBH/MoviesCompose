package com.brq.hellocompose.core.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.brq.hellocompose.R
import com.brq.hellocompose.ui.theme.Cyan700
import com.brq.hellocompose.ui.theme.Green100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBarCompose(title: Int) {

    CenterAlignedTopAppBar(
        title = {
            Text( stringResource(id = title),
                maxLines = 1,
                color = Cyan700,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Cyan700,
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.ExitToApp,
                    tint = Cyan700,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Green100)
    )
}


@Preview
@Composable
fun PreviewToolbar () {
    val onClickAction: () -> Unit = {}
    HomeToolBarCompose(R.string.home_toolbar_title_text)
}

@Preview
@Composable
fun PreviewToolbarWithSecondIcon () {
    val onClickAction: () -> Unit = {}
    HomeToolBarCompose(R.string.home_toolbar_title_text)
}