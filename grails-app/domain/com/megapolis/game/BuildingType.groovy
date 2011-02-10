package com.megapolis.game

abstract class BuildingType {

    int lucrativity
    String name
    String dirname
    int price

    Image ground
    Image large
    Image medium
    Image small

    static constraints = {
        ground(nullable: true)
        medium(nullable: true)
        small(nullable: true)
    }

    static hasMany = [upgrades: BuildingType]

    abstract int profit(Field field, Calendar lastWithdrawal)
}
