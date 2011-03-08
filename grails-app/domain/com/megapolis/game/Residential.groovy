package com.megapolis.game

class Residential extends BuildingType {
    private static final int RENT_PER_RESIDENT = 10

    def cityService

    int maxResidents

    static transients = ['cityService']

    void init(Building building) {
        building.residents = Math.round(0.1 * maxResidents)
    }

    int currentProfit(Building building) {
        // TODO residents based on lucrativity
        return building.residents * RENT_PER_RESIDENT * cityService.lucrativity(building.field)
    }

    long withdraw(Building building) {
        def profit = super.withdraw(building)
        // TODO residents based on lucrativity and lastWithdrawal
        if(profit >= 0) {
            if(building.residents < maxResidents) {
                Building.withTransaction {
                    building.residents = Math.min(maxResidents, (int) (building.residents + 0.1 * maxResidents))
                    building.save(flus: true)
                }
            }
        }
        return profit
    }

}
