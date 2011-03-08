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

        [name: player?.profile?.name, buildingCount: Building.byPlayer(player).count(), money: player?.money,
         income: income, profilePicture: player?.profilePicture]
    }
}
