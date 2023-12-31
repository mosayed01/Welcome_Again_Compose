package com.example.welcomecompose.presentation.screens.buy_tickets.composables


enum class ChairState {
    Available,
    Taken,
    Selected
}

fun ChairState.nextState(): ChairState {
    return when(this){
        ChairState.Available -> ChairState.Taken
        ChairState.Taken -> ChairState.Selected
        ChairState.Selected -> ChairState.Available
    }
}