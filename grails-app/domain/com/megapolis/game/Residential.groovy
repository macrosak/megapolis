package com.megapolis.game

class Residential extends BuildingType {

    int maxResidents

    void init(Building building) {
        building.residents = Math.round(0.1 * maxResidents)
    }

}
