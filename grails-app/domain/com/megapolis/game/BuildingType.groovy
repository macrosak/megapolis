package com.megapolis.game

import com.megapolis.game.player.Player

class BuildingType {
    private static final int MINIMUM_WITHDRAW_TIME = 10
    double lucrativity
    String name
    String dirname
    int price
     // in seconds
    int profitTime

    Image large
    Image medium
    Image small

    static constraints = {
        medium(nullable: true)
        small(nullable: true)
    }

    static hasMany = [upgrades: BuildingType]

    void init(Building building) {}

    int currentProfit(Building building) {0}

    long withdraw(Building building) {
        def currentProfit = currentProfit(building)
        def now = Calendar.instance
        def time = (now.getTimeInMillis() - building.lastWithdrawal.getTimeInMillis()) / 1000

        if(time < MINIMUM_WITHDRAW_TIME)
            return -1

        def profit = (long) (currentProfit * Math.log(time) / Math.log(profitTime))

        def player = building.owner
        Player.withTransaction {
            player.refresh()
            player.money += profit
            building.lastWithdrawal = now
            building.save()
            player.save(flush: true)
        }
        return profit
    }
}
