/*
 * Copyright 2010-2011 by MegapolisTeam
 *
 * This file is part of Megapolis.
 *
 * Megapolis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Megapolis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Megapolis.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.megapolis.game

import com.megapolis.game.player.Player

class BuildingType {
    private static final int MINIMUM_WITHDRAW_TIME = 10 * 60
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
