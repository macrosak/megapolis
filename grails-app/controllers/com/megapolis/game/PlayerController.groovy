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
import com.megapolis.FacebookController

class PlayerController extends FacebookController {

    def facebookService
    def cityService

    def index = { redirect action: 'profile' }

    def myBuildings = {
        def player = facebookService.player
        return [player: player, buildings: Building.byPlayer(player).list(), cityService: cityService]
    }

    def profile = {
        def player =  facebookService.player
        double income = 0

        def buildings = Building.byPlayer(player).list()

        buildings.each {building->
            income += building.currentProfit();
        }
        println player.profileJSON
        [name: player?.profile?.name, buildingCount: Building.byPlayer(player).count(), money: player?.money,
         income: income, profilePicture: player?.profilePicture]
    }
}
