package com.megapolis.game

class BuildingType {

    int lucrativity
    String name
    String dirname
    int price

    Image large
    Image medium
    Image small

    static constraints = {
        medium(nullable: true)
        small(nullable: true)
    }

    static hasMany = [upgrades: BuildingType]

    int withdraw(Field field, Calendar lastWithdrawal) {0}

    int currentProfit(Field field) {0}
}
