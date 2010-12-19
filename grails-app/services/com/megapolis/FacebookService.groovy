package com.megapolis

import grails.converters.JSON
import com.megapolis.game.player.Player
import grails.util.GrailsUtil

class FacebookService {

    def playerService

    static transactional = true

    static scope = "session"

    public static final String APP_ID = "167042136651099"
    public static final String GRAPH_URI = "https://graph.facebook.com/"
    public static final String AUTHORIZE_URI = GRAPH_URI + "oauth/authorize"
    public static final String ACCESS_TOKEN_URI = GRAPH_URI + "oauth/access_token"

//    private static final TEST_JSON = """{"id":"574721438","name":"Michal Knizek","first_name":"Michal","last_name":"Knizek","link":"http://www.facebook.com/mknizek","hometown":{"id":"108210209207966","name":"Prague"},"location":{"id":"108210209207966","name":"Prague"},"work":[{"employer":{"id":"109536262400378","name":"DataApex"},"start_date":"0000-00","end_date":"0000-00"}],"education":[{"school":{"id":"110610612299901","name":"\u010cVUT FEL"},"year":{"id":"119613371391707","name":"2012"},"concentration":[{"id":"114716941894593","name":"Otev\u0159en\u00e1 informatika"}],"type":"College"},{"school":{"id":"110610612299901","name":"\u010cVUT FEL"},"year":{"id":"119631034720930","name":"2010"},"concentration":[{"id":"112485162111573","name":"EAI"}],"type":"College"},{"school":{"id":"106558562715379","name":"SPSE Jecna"},"year":{"id":"166987080000025","name":"2007"},"type":"High School"}],"timezone":1,"locale":"en_US","verified":true,"updated_time":"2010-11-23T10:35:50+0000"}"""
    private static final TEST_JSON = """{"id":"574721439","name":"Michal Knizek","first_name":"Michal","last_name":"Knizek"}"""

    def me = null
    def meMap = null
    String accessToken

    def serviceMethod() {

    }

    def getProfileJSON() {
        if(GrailsUtil.environment == 'production') {
            if (!me) {
                me = new URL(GRAPH_URI + "me?" + accessToken).text
            }
            return me
        }
        return TEST_JSON
    }

    def getProfile() {
        if (!meMap) {
            meMap = JSON.parse(profileJSON)
        }
        return meMap
    }

    def getPlayer() {
        def id = profile?.id
        def player = Player.findByFacebookId(id)
        if(!player) {
            player = playerService.newPlayer(id, profileJSON)
        }
        return player
    }
}
