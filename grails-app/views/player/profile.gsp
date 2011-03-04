<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

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
