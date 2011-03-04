package com.megapolis.game

import com.megapolis.game.player.Player

class PlayerController {

    def facebookService

    def index = { redirect action: 'profile' }

    def myBuildings = {
        def player = facebookService.player
        return [player: player, buildings: Building.byPlayer(player).list()]
    }

    def profile = {
        def player =  facebookService.player

        def income = 0

        def buildings = Building.byPlayer(player).list()

        buildings.each {building->
            income += building.currentProfit();  // zapocitat lukrativitu ?
        }

        [name: player?.profile?.name, buildingCount: Building.byPlayer(player).count(), money: player?.money,
         income: income, profilePicture: player?.profilePicture]
    }
}
