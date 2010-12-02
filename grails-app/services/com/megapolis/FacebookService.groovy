package com.megapolis

import grails.converters.JSON

class FacebookService {

    static transactional = true

    static scope = "session"

    public static final String APP_ID = "167042136651099"
    public static final String GRAPH_URI = "https://graph.facebook.com/"
    public static final String AUTHORIZE_URI = GRAPH_URI+"oauth/authorize"
    public static final String ACCESS_TOKEN_URI = GRAPH_URI+"oauth/access_token"

    def me = null
    String accessToken

    def serviceMethod() {

    }

    def getProfile() {
      if(!me)
        me = new URL(GRAPH_URI+"me?"+accessToken).text
      return me
    }
}
