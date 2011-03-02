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
        [name: player?.profile?.name]
    }
}
