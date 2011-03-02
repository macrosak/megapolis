package com.megapolis.game

class Shop extends BuildingType {

    def cityService

    // in seconds
    int profitTime
    int salesRange
    int maxProfit
    int idealCustomersCount

    static transients = ['cityService']
}
