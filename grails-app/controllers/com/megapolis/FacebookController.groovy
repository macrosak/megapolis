package com.megapolis

import grails.util.GrailsUtil
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class FacebookController {
  static final def taglib = new ApplicationTagLib()
  static final String APP_ID = "167042136651099"
  static final String FACEBOOK_URI = "https://graph.facebook.com/oauth/authorize"
  static final String REDIRECT_URI = taglib.createLink(controller:"facebook",action:"oauth_redirect")
  static final String GRAPH_URI = "https://graph.facebook.com/oauth/access_token"

  def beforeInterceptor = {

    if(!session.accessToken && GrailsUtil.environment == 'production')
      redirect("${FACEBOOK_URI}?client_id=${APP_ID}&redirect_uri=${REDIRECT_URI}")
    return true
  }

  def oauth_redirect = {

    session.accessToken = new URL(GRAPH_URI+"?client_id=${APP_ID}&redirect_uri=${REDIRECT_URI}&client_secret=eb737aaffb98ae1cc196c5e2d88033de&code=${params.code}").text

  }
}
