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
    <area shape="rect" coords="19,34,37,52" href="${createLink(action:'showSmall', params:[posX: position.x + 1, posY: position.y - 1])}" alt="" title=""    />
    <area shape="rect" coords="35,18,53,36" href="${createLink(action:'showSmall', params:[posX: position.x + 1, posY: position.y + 1])}" alt="" title=""    />
    <area shape="rect" coords="19,1,37,19" href="${createLink(action:'showSmall', params:[posX: position.x - 1, posY: position.y + 1])}" alt="" title=""    />
    <area shape="rect" coords="3,18,21,36" href="${createLink(action:'showSmall', params:[posX: position.x - 1, posY: position.y - 1])}" alt="" title=""    />
    <area shape="rect" coords="19,18,37,35" href="${createLink(action:'show')}" alt="" title=""    />
    </map>
  <g:set var="zindex" value="${1}"/>
  <g:each in="${(-1 * viewConfig.fields.x)..viewConfig.fields.x}" var="j">
    <g:each in="${viewConfig.fields.y..(-1 *viewConfig.fields.y)}" var="i">
      <%
        def field = fields.find { it.coordX == position.x + j && it.coordY == position.y + i}

        def building = field?.building
        def image = building?.iso

        def height = image?.height ? image?.height / 2 : viewConfig.field.y
        def width = image?.width ? image?.width / 2  : viewConfig.field.x

        def bottom = ((i - j) * viewConfig.field.y / 2) + viewConfig.canvas.y / 2 - viewConfig.field.y / 2
        def left = ((j + i) * viewConfig.field.x / 2) - viewConfig.field.x / 2 + viewConfig.canvas.x / 2

        bottom += image?.offsetY ? image?.offsetY / 2 : 0
        left -= image?.offsetX ? image?.offsetX / 2  : 0
        left += viewConfig.field.x / 2

      %>
      <g:if test="${building}">
      <div id="${position.x + j};${position.y + i}"
      style="position:absolute; float: left;
      bottom: ${bottom}px;
      left:${left}px;
      width: ${width}px;
      height: ${height}px;
      z-index: ${building?.ground? 0 : zindex++};">
          <img src="${resource(dir: 'images/buildings/' + building.dirname, file: 'iso_small.png')}"/>
      </div>
      </g:if>
    </g:each>
  </g:each>

  </div>

  </body>
</html>
