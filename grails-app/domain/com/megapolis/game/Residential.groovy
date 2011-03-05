package com.megapolis.game

class Residential extends BuildingType {
    private static final int RENT_PER_RESIDENT = 10
    int maxResidents

    void init(Building building) {
        building.residents = Math.round(0.1 * maxResidents)
    }

    int currentProfit(Building building) {

    }

}
