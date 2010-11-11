package com.megapolis

import grails.util.GrailsUtil
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class FacebookController {
  def taglib = new ApplicationTagLib()
  static final String APP_ID = "167042136651099"
  static final String FACEBOOK_URI = "https://graph.facebook.com/oauth/authorize"
  static String REDIRECT_URI = null
  static final String GRAPH_URI = "https://graph.facebook.com/oauth/access_token"

  def beforeInterceptor = {
    if(!REDIRECT_URI)
        REDIRECT_URI = taglib.createLink(controller:"facebook",action:"oauth_redirect", absolute: true)
      
    if(!session.accessToken && GrailsUtil.environment == 'production')
      redirect(url: "${FACEBOOK_URI}?client_id=${APP_ID}&redirect_uri=${REDIRECT_URI}")
    return true
  }

  def oauth_redirect = {

    if(params.error_reason)
      redirect(action:"error", params:[reason:params.error_reason])

    session.accessToken = new URL(GRAPH_URI+"?client_id=${APP_ID}&redirect_uri=${REDIRECT_URI}&client_secret=eb737aaffb98ae1cc196c5e2d88033de&code=${params.code}").text
    redirect(controller: 'city')
  }

  def error = {

    return [reason:params.reason]

  }
}
