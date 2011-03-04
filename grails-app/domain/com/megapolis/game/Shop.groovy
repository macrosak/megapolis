package com.megapolis.game

class Shop extends BuildingType {

    def cityService

    // in seconds
    int profitTime
    int salesRange
    int maxProfit
    int idealCustomersCount

    static transients = ['cityService']

    int currentProfit(Field field) {
        int population = cityService.population(field, cityService.W)
        int neededShopCustomers = cityService.neededShopCustomers(field, cityService.W)

        if(neededShopCustomers > 0)
            return Math.min(maxProfit, Math.round(maxProfit*(double)population/(double)neededShopCustomers))
        else
            return 0
    }
}
