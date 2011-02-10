package com.megapolis.game

abstract class BuildingType {

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

    abstract int profit(Field field, Calendar lastWithdrawal)
}
