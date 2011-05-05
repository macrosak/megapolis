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
    <title>Profile</title>
  </head>
  <body>
    <img src="${profilePicture ? profilePicture : resource(dir: 'images/', file: 'default_profile_picture.png')}" style="vertical-align:middle;">
    <h1 style="display:inline; margin-left:15px;">${name}</h1>

    <%-- <p>Player rank: 332</p> --%>

    <p>Buildings: ${buildingCount}</p>

    <p>Money: $${money}</p>

    <p>Income: <span class="income">+$${income}</span></p>


  </body>
</html>
