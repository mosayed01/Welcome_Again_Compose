package com.example.welcomecompose.presentation.screens.booking

import com.example.welcomecompose.R

data class BookingUiState(
    val time: String = "2h 23m",
    val rateOfTenIMDp: Float = 6.8f,
    val rateOfTenIGN: Float = 4.0f,
    val rottenTomatoes: Int = 63,
    val title: String = "Fantastic Beasts: The \nSecrets of Dumbledore",
    val genres: List<String> = listOf(
        "Fantasy",
        "Adventure",
    ),
    val images: List<Int> = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_5,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_2,
        R.drawable.img_5,
        R.drawable.background,
    ),
    val overview: String = "Professor Albus Dumbledore knows the powerful," +
            "dark wizard Gellert Grindelwald is moving to seize" +
            "control of the wizarding world. Unable to stop him...",
)