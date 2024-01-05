package com.example.salawatime.presentation.ui.bottomnav

import com.example.salawatime.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    // for reminder
    object Reminder: BottomBarScreen(
        route = "report",
        title = "",
        icon = R.drawable.ic_bottom_report,
        icon_focused = R.drawable.ic_bottom_report_focused
    )
    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "",
        icon = R.drawable.ic_bottom_home,
        icon_focused = R.drawable.ic_bottom_home_focused
    )
    // for todo
    object ToDo: BottomBarScreen(
        route = "profile",
        title = "",
        icon = R.drawable.ic_bottom_profile,
        icon_focused = R.drawable.ic_bottom_profile_focused
    )

}