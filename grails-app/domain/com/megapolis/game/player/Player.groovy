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

package com.megapolis.game.player

import com.megapolis.game.Field
import grails.converters.JSON

class Player {

    long money
    long facebookId
    def profile
    def friends
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
