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
<%@ page import="com.megapolis.game.Residential" contentType="text/html;charset=UTF-8" %>
<div style="text-align:left">
    <img src="${resource(dir: 'images/buildings/' + building.type.dirname, file: building.type.large.filename)}" style="float:left;width:150px;height:auto;"/>
    <table style="padding-top:18px" cellpadding="5">
    	<tr>
    	<td>Owner:</td>
    	<td>${buildingOwner.profile.name}</td>
    	</tr>
      <tr>
        <td>Name:</td>
        <td>${building.type.name}</td>
      </tr>
      <g:if test="${building.type..instanceOf(Residential)}">
      <tr>
        <td>Residents:</td>
        <td>${building.residents}</td>
      </tr>
      </g:if>
    </table>
    </div>