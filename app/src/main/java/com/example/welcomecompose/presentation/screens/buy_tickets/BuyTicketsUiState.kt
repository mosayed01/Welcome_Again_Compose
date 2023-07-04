package com.example.welcomecompose.presentation.screens.buy_tickets

data class BuyTicketsUiState(
    val selectedDay: Day = Day(17, "Sun"),
    val selectedTime: String = "10:00",
    val price: Double = 100.00,
    val ticketsCount: Int = 5,
    val days: List<Day> = listOf(
        Day(14, "Thu"),
        Day(15, "Fri"),
        Day(16, "Sat"),
        Day(17, "Sun"),
        Day(18, "Mon"),
        Day(19, "Tue"),
        Day(20, "Tue"),
        Day(21, "Tue"),
        Day(22, "Tue"),
    ),
    val timeList: List<String> = listOf(
        "10:00",
        "12:30",
        "15:30",
        "18:00",
        "18:30",
        "19:00",
        "20:00",
    )
)

data class Day(val dayNumber: Int, val dayName: String)