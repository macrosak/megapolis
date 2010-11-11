<%@ page import="com.megapolis.game.Field; com.megapolis.game.Building" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'building.label', default: 'Building')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
Auth: ${session.accessToken}<br/><br/>
<g:link action="generate">Generate!</g:link><br/><br/>
<div style="height:500px; position: absolute; width:1200px; border:red 2px; overflow:hidden">
  <g:set var="zindex" value="${1000}"/>

  <g:each in="${0..12}" var="j">
    <g:each in="${12..-3}" var="i">
      <%
        def field = Field.findByCoordXAndCoordY(j, i)
        def building = field?.building
        def height = building?.height ?: 40
      %>
      <div style="position:absolute; float: left;
      bottom: ${j * 39}px;
      left:${i * 101 + j * 25}px;
      width: 101px;
      height: ${height}px;
      z-index: ${zindex--};">
        <g:if test="${building}">
          <img src="${resource(dir: 'images/buildings', file: building.filename)}"/>
        </g:if>
      </div>
    </g:each>
  </g:each>

</div>
</body>
</html>
