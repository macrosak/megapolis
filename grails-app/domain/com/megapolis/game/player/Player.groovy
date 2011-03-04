package com.megapolis.game.player

import com.megapolis.game.Field
import grails.converters.JSON

class Player {

    long money
    long facebookId
    def profile
    String profileJSON
    int homeX = 0
    int homeY = 0

    public String profilePicture // URL

    static hasMany = [fields: Field]
    static transients = ['profile']
    
    static constraints = {
        profileJSON(maxSize: 4068, nullable: false, blank: false)
    }

    def getProfile() {
        if(!profile)
            profile = JSON.parse(profileJSON)
        return profile
    }

}
