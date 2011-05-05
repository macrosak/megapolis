%{--
  - Copyright 2010-2011 by MegapolisTeam
  -
  - This file is part of Megapolis.
  -
  - Megapolis is free software: you can redistribute it and/or modify
  - it under the terms of the GNU Lesser General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or
  - (at your option) any later version.
  -
  - Megapolis is distributed in the hope that it will be useful,
  - but WITHOUT ANY WARRANTY; without even the implied warranty of
  - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  - GNU Lesser General Public License for more details.
  -
  - You should have received a copy of the GNU Lesser General Public License
  - along with Megapolis.  If not, see <http://www.gnu.org/licenses/>.
  --}%

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City</title>

    <g:importDialogs/>
    <g:javascript>
    jQuery(function() {
		jQuery( "#canvas" ).draggable();
	});

      jQuery(function() {
//        jQuery('#test').click(function() {
//          jQuery('.building').animate({opacity:0.1}, 2000)
//        })
      })
    </g:javascript>

  </head>
  <body>
  <g:dialog name="profileDialog" zIndex="10000" width="500" height="300" title="Profile">
    <table class="profileT">
        <tr><td rowspan="6" class="pdPhoto"><img alt="profilePhoto" src="${profilePicture ? profilePicture : resource(dir: 'images/', file: 'default_profile_picture.png')}"/></td>
        <tr><td class="pdName">${playerName}</td></tr>
        <tr><td class="pdBuildings">Buildings: ${buildingCount}</td></tr>
        <tr><td class="pdMoney">Money: $${player.money}</td></tr>
        <tr><td class="pdIncome">Income: <span class="income">+$${income}</span></td></tr>
	<tr><td></td></tr>
	</table>
  </g:dialog>

  <div style="position:relative">

  <div style="height: ${viewConfig.canvas.y}px;
  position: absolute;
  width: ${viewConfig.canvas.x}px;
  overflow:hidden;
  background-color: #adadad;
  /*background-color: red;*/
  border:1px solid black; ">

  <g:klikatko position="${position}" zoomAction="show" zoomParams="[zoom: nextZoom]"/>
  <g:set var="zindex" value="${1}"/>

  <div id="canvas" style="position: relative;width: ${viewConfig.canvas.x}px;height: ${viewConfig.canvas.y}px;">
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <g:cityField viewConfig="${viewConfig}" i="${i}" j="${j}"
              fields="${fields}" zindex="${zindex++}" position="${position}"
              type="${zoom}" background="${background}"/>
    </g:each>
  </g:each>
    </div>

  <div id="logoSmall">
    <img alt="megapolisLogo" src="${resource(dir: 'images/', file: 'logo_small.png')}"/>
  </div>
  <g:render template="/layouts/menu"/>
  </div>

  <div style="position:absolute; top:${viewConfig.canvas.y}px">

      <g:each in ="${player.friends}" var="friend">${friend.profile.name}<br></g:each>

  </div>

  </div>

  </body>
</html>
