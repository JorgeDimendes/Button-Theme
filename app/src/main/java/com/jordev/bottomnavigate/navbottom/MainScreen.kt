package com.jordev.bottomnavigate.navbottom

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jordev.bottomnavigate.datastores.UserPreference
import com.jordev.bottomnavigate.screens.HomePage
import com.jordev.bottomnavigate.screens.NotificationPages
import com.jordev.bottomnavigate.screens.SettingsPage
import com.jordev.bottomnavigate.ui.theme.MyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navItemList = listOf(
        NavItem(label = "Home", icon = Icons.Default.Home),
        NavItem(label = "Favorito", icon = Icons.Default.FavoriteBorder),
        NavItem(label = "Tema", icon = Icons.Default.Settings)
    )
    var selectedIndex by remember { mutableStateOf(0) }
    var isDarkMode by remember { mutableStateOf(false) }

    var context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        UserPreference.getTema(context).collect { savedTheme ->
            isDarkMode  = savedTheme
        }
    }

    MyTheme(
        theme = if (isDarkMode) "2" else "1"
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize().padding(bottom = 0.dp),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
                    title = { Text(text = "") },
                    actions = {
                        //Icon(imageVector = Icons.Default.Home, contentDescription = null)
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { newValue ->
                                isDarkMode = newValue
                                scope.launch {
                                    UserPreference.salvarTema(context, newValue)
                                }
                            },
                            modifier = Modifier.padding(end = 15.dp)
                        )
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                            label = { Text(text = navItem.label)}
                        )
                    }
                }
            }
        ) {paddingValues ->
            ContentScreen(modifier = Modifier.padding(paddingValues), selectedIndex)
        }
    }
}
@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int){
    when(selectedIndex){
        0 -> HomePage()
        1 -> NotificationPages()
        2 -> SettingsPage()
    }
}