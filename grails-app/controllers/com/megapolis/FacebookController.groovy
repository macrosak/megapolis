package com.megapolis

class FacebookController {

  static final String appID = "167042136651099"
  static final String facebook_uri = "https://graph.facebook.com/oauth/authorize"
  static final String redirect_uri = createLink(controller:"facebook",action:"oauth_redirect")
  static final String graph_uri = "https://graph.facebook.com/oauth/access_token"

  def index = {

    if(!session.accessToken && GrailsUtil.environment == 'production')
      redirect("${facebook_uri}?client_id=${appID}&redirect_uri=${redirect_uri}")
  
  }

  def oauth_redirect = {

    session.accessToken = new URL(graph_uri+"?client_id=${appID}&redirect_uri=${redirect_uri}&client_secret=eb737aaffb98ae1cc196c5e2d88033de&code=${params.code}").text

  }
}
