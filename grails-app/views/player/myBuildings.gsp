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
    <title>My Buildings</title>
  </head>
  <body>
  <g:render template="/shared/message"/>
  <g:each in="${buildings}" var="building">
    <div>
      <img src="${resource(dir: 'images/buildings/' + building.type.dirname, file: building.type.large.filename)}" style="float:left;"/>
    <table>
      <tr>
        <td>Name:</td>
        <td>${building.type.name}</td>
      </tr>
      <tr>
        <td>Position:</td>
        <td>[${building.field.coordX}, ${building.field.coordY}]
        <g:link controller="city" action="show" params="[posX: building.field.coordX, posY: building.field.coordY]">go</g:link> </td>
      </tr>
      <tr>
        <td>Residents:</td>
        <td>${building.residents}</td>
      </tr>
      <tr>
        <td>Last Withdrawal:</td>
        <td><g:formatDate date="${building.lastWithdrawal}"/></td>
      </tr>
      <tr>
        <td>Current lucrativity:</td>
        <td><g:formatNumber number="${cityService.lucrativity(building.field)}" maxFractionDigits="2"/></td>
      </tr>
      <tr>
        <td>Current Profit:</td>
        <td>${building.currentProfit()}</td>
      </tr>
      <tr>
        <td colspan="2">
        <div style="background-color:red">
          <g:set var="now" value="${Calendar.getInstance()}"/>
          <div style="width:${(now.getTimeInMillis() - building.lastWithdrawal?.getTimeInMillis()) / 10 / building.type.profitTime}%; background-color: green; display:inline-block;">
            &nbsp;
          </div>
          &nbsp;
        </div>
        </td>
      </tr>
      <tr>
        <td colspan="2"><g:link controller="building" action="withdraw" id="${building.id}">Withdraw</g:link> </td>
      </tr>
    </table>

    </div>
    <div style="clear:both;"></div>
    <hr/>
    </div>
  </g:each>

  </body>
</html>
