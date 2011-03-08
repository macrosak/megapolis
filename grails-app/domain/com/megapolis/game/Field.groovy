package com.megapolis.game

import com.megapolis.game.player.Player

class Field {
    int coordX
    int coordY
    Building building
    Player owner
    
    static constraints = {
        building(nullable: true)
        owner(nullable: true)
        coordX(unique: 'coordY')
    }

    int price() {
        return 200;
    }
}
