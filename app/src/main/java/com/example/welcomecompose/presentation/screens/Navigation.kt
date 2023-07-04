package com.example.welcomecompose.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.welcomecompose.presentation.screens.booking.BookingScreen
import com.example.welcomecompose.presentation.screens.buy_tickets.BuyTicketsScreen
import com.example.welcomecompose.presentation.screens.home.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Booking.route) {
            BookingScreen(navController)
        }
        composable(Screens.BuyTickets.route) {
            BuyTicketsScreen(navController)
        }
    }
}

sealed class Screens(val route: String) {
    object Booking : Screens("booking")
    object BuyTickets : Screens("buy_tickets")
    object Home : Screens("home")
}