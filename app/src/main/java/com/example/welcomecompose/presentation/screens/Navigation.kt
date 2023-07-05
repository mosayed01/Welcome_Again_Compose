package com.example.welcomecompose.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.welcomecompose.presentation.screens.booking.BookingScreen
import com.example.welcomecompose.presentation.screens.buy_tickets.BuyTicketsScreen
import com.example.welcomecompose.presentation.screens.home.HomeScreen
import com.example.welcomecompose.presentation.screens.home.composables.CustomBottomNav


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screens.Home.route

    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = currentRoute == Screens.Home.route) {
                CustomBottomNav { navController.navigate(Screens.BuyTickets.route) }
            }
        },
    ) { screenPadding ->
        NavHost(navController = navController, startDestination = Screens.Home.route) {
            composable(Screens.Home.route) {
                HomeScreen(navController = navController, screenPadding = screenPadding)
            }
            composable(Screens.Booking.route) {
                BookingScreen(navController = navController, screenPadding = screenPadding)
            }
            composable(Screens.BuyTickets.route) {
                BuyTicketsScreen(navController = navController, screenPadding = screenPadding)
            }
        }
    }

}

sealed class Screens(val route: String) {
    object Booking : Screens("booking")
    object BuyTickets : Screens("buy_tickets")
    object Home : Screens("home")
}