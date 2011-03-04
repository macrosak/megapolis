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
    <img style="position:absolute; z-index:1000" id="klikatko" src="${resource( dir:'images', file: 'mapcontrols.png')}" usemap="#_klikatko" border="0" width="59" height="59" alt="" />
    <map id="_klikatko" name="_klikatko">
    <area shape="rect" coords="19,34,37,52" href="${createLink(action:'show', params:[posX: position.x + 1, posY: position.y - 1])}" alt="" title=""    />
    <area shape="rect" coords="35,18,53,36" href="${createLink(action:'show', params:[posX: position.x + 1, posY: position.y + 1])}" alt="" title=""    />
    <area shape="rect" coords="19,1,37,19" href="${createLink(action:'show', params:[posX: position.x - 1, posY: position.y + 1])}" alt="" title=""    />
    <area shape="rect" coords="3,18,21,36" href="${createLink(action:'show', params:[posX: position.x - 1, posY: position.y - 1])}" alt="" title=""    />
    <area shape="rect" coords="19,18,37,35" href="${createLink(action:'showSmall')}" alt="" title=""    />
    </map>
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <g:cityField viewConfig="${viewConfig}" i="${i}" j="${j}" fields="${fields}" zindex="${zindex++}" position="${position}"/>
    </g:each>
  </g:each>

  </div>

  </body>
</html>
