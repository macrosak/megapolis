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

    boolean withdraw(Building building) {
        println "super!"
        def currentProfit = currentProfit(building)
        def now = Calendar.instance
        def time = (now.getTimeInMillis() - building.lastWithdrawal.getTimeInMillis()) / 1000
        println "time: $time"

        if(time < MINIMUM_WITHDRAW_TIME)
            return false

        def profit = currentProfit * Math.log(time) / Math.log(profitTime)
        def player = building.owner
        Player.withTransaction {
            player.refresh()
            player.money += profit
            building.lastWithdrawal = now
            log.debug("profit: $profit, money: $player.money" )
            building.save()
            player.save(flush: true)
        }
        return true
    }
}
