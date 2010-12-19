<%@ page import="com.megapolis.game.Field; com.megapolis.game.Building" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'building.label', default: 'Building')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
Player: ${player} ${player.dump()}<br/>
View: ${viewConfig.field.y}<br/>
<g:link action="generate">Generate!</g:link><br/><br/>
<div style="height: ${viewConfig.canvas.y}px;
  position: absolute;
  width: ${viewConfig.canvas.x}px;
  overflow:hidden;
  background-color: #adadad;">
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <%
        def field = fields.find { it.coordX == position.x + j && it.coordY == position.y + i}

        def building = field?.building
        def image = building?.iso

        def height = image?.height/2 ?: viewConfig.field.y
        def width = image?.width/2 ?: viewConfig.field.x

        def bottom = ((i - j) * viewConfig.field.y / 2) + viewConfig.canvas.y / 2 - viewConfig.field.y / 2
        def left = ((j + i) * viewConfig.field.x / 2) - viewConfig.field.x / 2 + viewConfig.canvas.x / 2

        bottom += image?.offsetY/2 ?: 0
        left -= image?.offsetX/2 ?: 0
        left += viewConfig.field.x / 2

      %>
      <g:if test="${building && building.dirname != 'background'}">
      <div id="${position.x + j};${position.y + i}"
      style="position:absolute; float: left;
      bottom: ${bottom}px;
      left:${left}px;
      width: ${width}px;
      height: ${height}px;
      z-index: ${building?.ground? 0 : zindex++};">

          %{--<table width="${width}" border="0" cellspacing="0" cellpadding="0" height="${height}">--}%
            %{--<tr background="${resource(dir: 'images/buildings/' + building.filename, file: 'iso.png')}">--}%
              %{--<td>[i: ${i}, j: ${j}]</td>--}%
            %{--</tr>--}%
          %{--</table>--}%
          <img src="${resource(dir: 'images/buildings/' + building.dirname, file: 'iso_small.png')}"/>

      </div>
      </g:if>
    </g:each>
  </g:each>

</div>
</body>
</html>