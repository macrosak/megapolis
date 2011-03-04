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
        def population = cityService.population(field, cityService.W)
        def neededShopCustomers = cityService.neededShopCustomers(field, cityService.W)

        if(neededShopCustomers > 0)
            return Math.min(maxProfit, Math.round(maxProfit*population/neededShopCustomers))
        else
            return 0
    }
}
