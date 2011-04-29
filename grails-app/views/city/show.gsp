<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City</title>
    <g:importDialogs/>
  </head>
  <body>
  <input type="button" onclick="profileDialog.show()">
  <g:dialog name="profileDialog" zIndex="10000" width="600" height="300" top="50" left="80">
    <img src="${profilePicture ? profilePicture : resource(dir: 'images/', file: 'default_profile_picture.png')}" style="vertical-align:middle;">
    <h1 style="display:inline; margin-left:15px;">${name}</h1>

    <p>Buildings: ${buildingCount}</p>

    <p>Money: $${money}</p>

    <p>Income: <span class="income">+$${income}</span></p>
  </g:dialog>

  <div style="height: ${viewConfig.canvas.y}px;
  position: absolute;
  width: ${viewConfig.canvas.x}px;
  overflow:hidden;
  background-color: #adadad;
  border:1px solid black; ">
  <g:klikatko position="${position}" zoomAction="show" zoomParams="[zoom: nextZoom]"/>
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <g:cityField viewConfig="${viewConfig}" i="${i}" j="${j}"
              fields="${fields}" zindex="${zindex++}" position="${position}"
              type="${zoom}" background="${background}"/>
    </g:each>
  </g:each>
  <div id="logoSmall">
    <img src="${resource(dir: 'images/', file: 'logo_small.png')}"/>
  </div>
  <g:render template="/layouts/menu"/>

  </div>

  </body>
</html>
