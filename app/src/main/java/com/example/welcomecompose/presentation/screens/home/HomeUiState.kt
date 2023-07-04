package com.example.welcomecompose.presentation.screens.home

import com.example.welcomecompose.R

data class HomeUiState(
    val nowShowingChip: ChipUiState = ChipUiState("Now Showing", true),
    val comingSoonChip: ChipUiState = ChipUiState("Coming Soon", false),
    val images: List<Int> = listOf(
        R.drawable.img_1,
        R.drawable.img_3,
        R.drawable.img_2,
    ),
    val time: String = "2h 23m",
    val title: String = "Fantastic Beasts: The \nSecrets of Dumbledore",
    val genres: List<String> = listOf(
        "Fantasy",
        "Adventure",
        "Adventure",
        "Adventure",
        "Adventure",
        "Adventure",
        "Adventure",
    ),
) {
    data class ChipUiState(
        val title: String,
        val isSelected: Boolean = false
    )
}
