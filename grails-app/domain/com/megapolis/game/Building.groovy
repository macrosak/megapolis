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

class Building {

    int residents = 0
    Calendar lastWithdrawal
    BuildingType type

    static belongsTo = [field: Field]

    static constraints = {
        lastWithdrawal(nullable: true)
    }

    static namedQueries = {
        byPlayer { player ->
            field {
                owner {
                    eq('id', player.id)
                }
            }
        }
    }

    void init() {
        type.init(this)
        lastWithdrawal = Calendar.getInstance()
    }

    int currentProfit() {
        return type.currentProfit(this)
    }

    long withdraw() {
        return type.withdraw(this)
    }

    def getOwner() {
        return field.owner
    }
}
