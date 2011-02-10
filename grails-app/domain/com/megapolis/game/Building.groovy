package com.megapolis.game

class Building {

    int residents = 0
    Calendar lastWithdrawal
    BuildingType type

    static constraints = {
        lastWithdrawal(nullable: true)
    }
}
