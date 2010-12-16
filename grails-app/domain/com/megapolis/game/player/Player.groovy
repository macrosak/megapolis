package com.megapolis.game.player

import com.megapolis.game.Field

class Player {

    long money
    int facebookId

    static hasMany = [fields: Field]
    
    static constraints = {
    }
}
