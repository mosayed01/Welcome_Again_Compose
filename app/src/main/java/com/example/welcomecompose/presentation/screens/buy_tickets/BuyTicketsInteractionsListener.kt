package com.example.welcomecompose.presentation.screens.buy_tickets

interface BuyTicketsInteractionsListener {
    fun onClickExit()
    fun onClickBuy()
    fun doWhenSelectDay(day: Day)
    fun doWhenSelectHour(hour: String)
}