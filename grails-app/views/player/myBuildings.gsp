<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My Buildings</title>
  </head>
  <body>

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
        <td>${building.lastWithdrawal}</td>
      </tr>
      <tr>
        <td>Current Profit:</td>
        <td>${building.currentProfit()}</td>
      </tr>
    </table>

    </div>
    <div style="clear:both;"></div>
    <hr/>
    </div>
  </g:each>

  </body>
</html>
