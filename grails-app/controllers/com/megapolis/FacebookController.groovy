package com.megapolis

import grails.util.GrailsUtil
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

class FacebookController {
  def taglib = new ApplicationTagLib()
  def facebookService
  
  static String REDIRECT_URI = null

  def beforeInterceptor = {
    if(!REDIRECT_URI)
        REDIRECT_URI = taglib.createLink(controller:"facebook",action:"oauth_redirect", absolute: true)
      
    if(!facebookService.accessToken && GrailsUtil.environment == 'production')
    {
      session.url = "${FacebookService.AUTHORIZE_URI}?client_id=${FacebookService.APP_ID}&redirect_uri=${REDIRECT_URI}"
      redirect(url: "${FacebookService.AUTHORIZE_URI}?client_id=${FacebookService.APP_ID}&redirect_uri=${REDIRECT_URI}")
    }
    return true
  }

  def oauth_redirect = {
    if(params.error_reason)
      redirect(action:"error", params:[reason:params.error_reason])
    try {
        facebookService.accessToken = new URL(FacebookService.ACCESS_TOKEN_URI+"?client_id=${FacebookService.APP_ID}&redirect_uri=${REDIRECT_URI}&client_secret=eb737aaffb98ae1cc196c5e2d88033de&code=${params.code}").text
    } catch (Exception e) {

    }
    redirect(controller: 'city')
  }

  def error = {

    return [reason:params.reason]

  }
}
