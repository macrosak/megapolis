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

<%@ page import="com.megapolis.game.Ground; com.megapolis.game.BuildingType; com.megapolis.game.Field; com.megapolis.game.Building" %>
<html>
<head >
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'building.label', default: 'Building')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
  <g:importDialogs />
</head>
<body>

<g:javascript>

jQuery(function() {
  jQuery( "#canvas" ).draggable();
});

function buy(x, y, p) {
  var coords = "[" + x + "; " + y + "]"
  var divId = x + ";" + y
  if($('buyList').innerHTML.indexOf(coords) == -1 ) {
    $('buyFormHidden').innerHTML += "<input type='hidden' name='field' value='" + divId + "' id='hidden" + divId + "'/>";
    if($('buyList').innerHTML != "")
      $('buyList').innerHTML += ",&nbsp;"
    $('buyList').innerHTML += coords;
    $(divId).style.background = '#ffff00';
    var price = parseInt($('priceSpan').innerHTML);
    var money = parseInt($('moneySpan').innerHTML);
    price += p;
    if(money < price) {
      $('priceOuterSpan').style.color = '#ff0000';
      $('buyButton').disabled = true;
    }
    $('priceSpan').innerHTML = price;
  } else {
    var hidden = $('hidden' + divId);
    $('buyFormHidden').removeChild(hidden);
    $(divId).style.background = '#0000ff';
    var list = $('buyList').innerHTML;
    list = list.replace(",&nbsp;" + coords, "");
    list = list.replace(coords, "");
    $('buyList').innerHTML = list;
    var price = parseInt($('priceSpan').innerHTML);

    var money = parseInt($('moneySpan').innerHTML);
    price -= p;
    if(money >= price) {
      $('priceOuterSpan').style.color = '#000000';
      $('buyButton').disabled = false;
    }
    $('priceSpan').innerHTML = price;
  }
}

  function build(x, y, price) {
    $('buildCoord').value = x + ";" + y
    buildDialog.show()
  }

  function buildSubmit(id) {
    $('buildingId').value = id
    return true
  }

</g:javascript>
<g:render template="/shared/message"/>
  <div id="buyList" style="display: none;"></div>


<g:dialog name="buildDialog" zIndex="10000" title="Build" draggable="true" width="450" height="600" resizable="true">
  <div id="buildDialogDiv" style="z-index:10000">
    <form action="build">
      <input type="hidden" name="field" value="" id="buildCoord"/>
      <input type="hidden" name="buildingId" value="" id="buildingId"/>
      <g:each in="${BuildingType.list().findAll { !it.instanceOf(Ground)}}" var="b">
      <div>
        <div>
        <h2>${b.name}</h2>
        <input type="image" src="${resource(dir: 'images/buildings/' + b.dirname, file: b.medium.filename)}" onclick="buildSubmit(${b.id})"/>
        <br>
        Price: $${b.price}
        </div>
        
      </div>
    </g:each>
    </form>

    <button onclick="buildDialog.hide()">Cancel</button>

  </div>
</g:dialog>

<g:dialog name="profileDialog" zIndex="10000" width="500" height="300" title="Profile">
    <table class="profileT">
        <tr><td rowspan="6" class="pdPhoto"><img alt="profilePhoto" src="${player?.profilePicture ?: resource(dir: 'images/', file: 'default_profile_picture.png')}"/></td>
        <tr><td class="pdName">${player?.profile?.name}</td></tr>
        <tr><td class="pdBuildings">Buildings: ${buildingCount}</td></tr>
        <tr><td class="pdMoney">Money: $${player.money}</td></tr>
        <tr><td class="pdIncome">Income: <span class="income">+$${income}</span></td></tr>
	<tr><td></td></tr>
	</table>
  </g:dialog>

<div style="height: ${viewConfig.canvas.y}px;
position: absolute;
width: ${viewConfig.canvas.x}px;
overflow:hidden;
background-color: #adadad;">
  %{--<img style="position:absolute; z-index:1000" id="klikatko" src="${resource( dir:'images', file: 'mapcontrols.png')}" usemap="#_klikatko" border="0" width="59" height="59" alt="" />--}%
    %{--<map id="_klikatko" name="_klikatko">--}%
    %{--<area shape="rect" coords="19,34,37,52" href="${createLink(action:'buyBuild', params:[posY: position.y - 1])}" alt="" title=""    />--}%
    %{--<area shape="rect" coords="35,18,53,36" href="${createLink(action:'buyBuild', params:[posX: position.x + 1])}" alt="" title=""    />--}%
    %{--<area shape="rect" coords="19,1,37,19" href="${createLink(action:'buyBuild', params:[posY: position.y + 1])}" alt="" title=""    />--}%
    %{--<area shape="rect" coords="3,18,21,36" href="${createLink(action:'buyBuild', params:[posX: position.x - 1])}" alt="" title=""    />--}%
    %{--<area shape="rect" coords="19,18,37,35" href="${createLink(action:'buyBuild')}" alt="" title=""    />--}%
    %{--</map>--}%
  <g:klikatko position="${position}"/>

  <div id="canvas" style="position: relative;width: ${viewConfig.canvas.x}px;height: ${viewConfig.canvas.y}px;">
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <%
        def field = fields.find { it.coordX == position.x + j && it.coordY == position.y + i}

//        def building = field?.building
//        def image = building?.top
//
        def height = viewConfig.field.y
        def width = viewConfig.field.x

        def bottom = (i * viewConfig.field.y) + viewConfig.canvas.y / 2 - viewConfig.field.y
        def left = (j * viewConfig.field.x) - viewConfig.field.x + viewConfig.canvas.x / 2
//
//        if(image)
//          left -= image.width - viewConfig.field.x
      %>
      %{--<g:if test="${building && building.dirname != 'background'}">--}%

        %{--<div id="${position.x + j};${position.y + i}"--}%
                %{--style="position:absolute; float: left;--}%
                %{--bottom: ${bottom}px;--}%
                %{--left:${left}px;--}%
                %{--width: ${width}px;--}%
                %{--height: ${height}px;--}%
                %{--z-index: 1;">--}%

          %{--<img src="${resource(dir: 'images/buildings/' + building.dirname, file: image.filename)}"/>--}%

        %{--</div>--}%
      %{--</g:if>--}%
      <g:if test="${field}">
        <%
          def color = field?.owner == null? '#0000ff': '#ff0000'
          def action = field?.owner == null? 'buy': 'null'

          if(field?.building?.type?.instanceOf(Ground)) {
            action = 'null'
            color = '#aaaaaa'
          }

          if(field?.owner == player && field?.building == null) {
            action = 'build'
            color = '#00ff00'
          }


        %>
        <a href="#" onclick="${action}(${position.x + j}, ${position.y + i}, ${field?.price()})">
          <div id="${position.x + j};${position.y + i}"
                  style="position:absolute; float: left;
                  background-color: ${color};
                  bottom: ${bottom}px;
                  left:${left}px;
                  width: ${width -1}px;
                  height: ${height -1}px;
                  z-index: 0;
                  border:1px solid black;"
                  title="Price: $${field?.price()}">
          </div>
        </a>
      </g:if>

    </g:each>
  </g:each>
    </div>
    <div id="logoSmall">
    <img alt="megapolisLogo" src="${resource(dir: 'images/', file: 'logo_small.png')}"/>
  </div>
  <g:render template="/layouts/menu"/>
  <div id="buyDiv">
  <table>
    <tr>
      <td style="width: 50px">Money:</td>
      <td>$<span id="moneySpan">${player.money}</span></td>
    </tr>
    %{--<tr>--}%
      %{--<td style="width: 50px">Buy:</td>--}%
      %{--<td><div id="buyList"></div></td>--}%
    %{--</tr>--}%
    <tr>
      <td style="width: 50px">Price:</td>
      <td><span id="priceOuterSpan">$<span id="priceSpan">0</span></span></td>
    </tr>
    <tr>
      <td colspan="2" align="left">
        <g:form action="buy">
          <span id="buyFormHidden"></span>
          <g:submitButton name="buy" value="Buy land" id="buyButton"/>
        </g:form>
      </td>
    </tr>
  </table>
  </div>
 </div>



</body>
</html>
