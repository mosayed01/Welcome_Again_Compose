package com.example.welcomecompose.presentation.composables.ui_models


enum class ChairState {
    Available,
    Taken,
    Selected
}

fun ChairState.nextState(): ChairState{
    return when(this){
        ChairState.Available -> ChairState.Taken
        ChairState.Taken -> ChairState.Selected
        ChairState.Selected -> ChairState.Available
    }
}