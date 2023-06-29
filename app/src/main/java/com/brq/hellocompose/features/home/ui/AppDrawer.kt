package com.brq.hellocompose.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brq.hellocompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    route: String,
    modifier: Modifier = Modifier,
    navigateToOther: () -> Unit = {},
    navigateToAnother: () -> Unit = {},
    closeDrawer: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)
        Spacer(modifier = Modifier.padding(2.dp))
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            selected = route == "",
            onClick = {
                navigateToOther()
                closeDrawer()
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint =  MaterialTheme.colorScheme.primary
                    ) },
            shape = MaterialTheme.shapes.small
        )

        NavigationDrawerItem(
            label = { 
                Text(
                    text = stringResource(id = R.string.exit),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary

                    ) },
            selected = route == "",
            onClick = {
                navigateToAnother()
                closeDrawer()
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint =  MaterialTheme.colorScheme.primary
                    ) },
            shape = MaterialTheme.shapes.small
        )
    }
}


@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Image(
            painterResource(id = R.drawable.tmdbicon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = stringResource(id = R.string.app_drawer_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerHeaderPreview() {
    AppDrawer(modifier = Modifier, route = "")
}