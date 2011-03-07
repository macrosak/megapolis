package com.megapolis.game

class Shop extends BuildingType {

    def cityService

    int salesRange
    int maxProfit
    int idealCustomersCount

    static transients = ['cityService']

    int currentProfit(Building building) {
        def field = building.field
        int population = cityService.population(field, cityService.W)
        int neededShopCustomers = cityService.neededShopCustomers(field, cityService.W)
        double lucrativity = cityService.lucrativity(field)

        if(neededShopCustomers > 0)
            return Math.min(maxProfit, Math.round(lucrativity*maxProfit*(double)population/(double)neededShopCustomers))
        else
            return 0
    }
}
