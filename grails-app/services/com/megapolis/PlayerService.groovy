package com.megapolis

import com.megapolis.game.player.Player

class PlayerService {
    static final int INITIAL_MONEY = 10000

    def newPlayer(id, profileJSON) {
        new Player(facebookId: id, profileJSON: profileJSON, money: INITIAL_MONEY).save()
    }
}
