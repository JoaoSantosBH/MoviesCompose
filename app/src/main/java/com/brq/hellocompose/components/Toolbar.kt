package com.brq.hellocompose.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.brq.hellocompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBarCompose(title: Int) {

    CenterAlignedTopAppBar(
        title = {
            Text( stringResource(id = title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = ""
                )
            }
        }
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