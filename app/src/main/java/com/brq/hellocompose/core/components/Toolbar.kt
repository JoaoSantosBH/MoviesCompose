package com.brq.hellocompose.core.components

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.brq.hellocompose.R
import com.brq.hellocompose.core.navigation.Screen
import com.brq.hellocompose.ui.theme.Cyan700
import com.brq.hellocompose.ui.theme.Green100
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBarCompose(
    title: Int,
    navController: NavHostController,
    menuClick: () -> Unit
) {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        title = {
            Text( stringResource(id = title),
                maxLines = 1,
                color = Cyan700,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                menuClick.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Cyan700,
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.LoginScreen.route)
            }
            ) {
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


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewToolbar () {
    val navController = rememberAnimatedNavController()
    val menuClick: () -> Unit = {}
    HomeToolBarCompose(R.string.home_toolbar_title_text, navController, menuClick)
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewToolbarWithSecondIcon () {
    val navController = rememberAnimatedNavController()
    val menuClick: () -> Unit = {}
    HomeToolBarCompose(R.string.home_toolbar_title_text, navController, menuClick)
}