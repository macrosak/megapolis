package com.megapolis.game.player

import com.megapolis.game.Field

class Player {

    static hasMany = [fields: Field]
    static constraints = {
    }
}
