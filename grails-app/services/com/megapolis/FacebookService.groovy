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

package com.megapolis

import grails.converters.JSON
import com.megapolis.game.player.Player
import grails.util.GrailsUtil
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

class FacebookService {

    def playerService

    static transactional = true

    static scope = "session"

    public static final String APP_ID = "167042136651099"
//    public static final String APP_ID = "176373869049919"
    public static final String GRAPH_URI = "https://graph.facebook.com/"
    public static final String AUTHORIZE_URI = GRAPH_URI + "oauth/authorize"
    public static final String ACCESS_TOKEN_URI = GRAPH_URI + "oauth/access_token"

//    private static final TEST_JSON = """{"id":"574721438","name":"Michal Knizek","first_name":"Michal","last_name":"Knizek","link":"http://www.facebook.com/mknizek","hometown":{"id":"108210209207966","name":"Prague"},"location":{"id":"108210209207966","name":"Prague"},"work":[{"employer":{"id":"109536262400378","name":"DataApex"},"start_date":"0000-00","end_date":"0000-00"}],"education":[{"school":{"id":"110610612299901","name":"\u010cVUT FEL"},"year":{"id":"119613371391707","name":"2012"},"concentration":[{"id":"114716941894593","name":"Otev\u0159en\u00e1 informatika"}],"type":"College"},{"school":{"id":"110610612299901","name":"\u010cVUT FEL"},"year":{"id":"119631034720930","name":"2010"},"concentration":[{"id":"112485162111573","name":"EAI"}],"type":"College"},{"school":{"id":"106558562715379","name":"SPSE Jecna"},"year":{"id":"166987080000025","name":"2007"},"type":"High School"}],"timezone":1,"locale":"en_US","verified":true,"updated_time":"2010-11-23T10:35:50+0000"}"""
    private static final TEST_JSON = """{"id":"574721439","name":"Frantisek Omacka","first_name":"Michal","last_name":"Knizek"}"""

    def me = null
    def meMap = null
    String accessToken    // obalit metodou, ktera overi jestli accessToken je validni, pokud ne udelat znovu oauth

    def serviceMethod() {

    }

    def getProfileJSON() {
        //if(GrailsUtil.environment == 'production') {
            if (!me) {
                me = new URL(GRAPH_URI + "me?" + accessToken).text
            }
            return me
        //}
        //return TEST_JSON
    }

    def getProfile() {
        if (!meMap) {
            meMap = JSON.parse(profileJSON)
        }
        return meMap
    }

    Player getPlayer() {
        def id = profile?.id
        def player = Player.findByFacebookId(id)
        if(!player) {
            player = playerService.newPlayer(id, profileJSON)
        }
        player.profilePicture = this.getProfilePicture()
        return player
    }

    // returns URL of profile picture
    String getProfilePicture() {
        try {
            def url = new URL(GRAPH_URI + "me/picture?type=large&" + accessToken) //.openConnection()
            def conn = (HttpsURLConnection)url.openConnection()
            conn.setFollowRedirects(false)
            if(conn.getResponseCode() == 302) {
                return conn.getHeaderField("Location")
            }
        } catch (IOException) {}
        return null
    }
}
