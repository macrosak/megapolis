<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>City</title>

  </head>
  <body>

  <div style="height: ${viewConfig.canvas.y}px;
  position: absolute;
  width: ${viewConfig.canvas.x}px;
  overflow:hidden;
  background-color: #adadad;
  border:1px solid black; ">
  <g:klikatko position="${position}" zoomAction="showSmall" />
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <g:cityField viewConfig="${viewConfig}" i="${i}" j="${j}" fields="${fields}" zindex="${zindex++}" position="${position}" type="small"/>
    </g:each>
  </g:each>

  </div>

  </body>
</html>