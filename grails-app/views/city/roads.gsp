<%@ page import="com.megapolis.game.Field; com.megapolis.game.Building" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'building.label', default: 'Building')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
  <g:importDialogs />
</head>
<body>

<g:javascript>

function road(x,y) {
  var coords = "[" + x + "; " + y + "]"
  var divId = x + ";" + y
  if($('buyList').innerHTML.indexOf(coords) == -1 ) {
    $('buyFormHidden').innerHTML += "<input type='hidden' name='field' value='" + divId + "' id='hidden" + divId + "'/>";
    if($('buyList').innerHTML != "")
      $('buyList').innerHTML += ",&nbsp;"
    $('buyList').innerHTML += coords;
    $(divId).style.background = '#ffff00'
  } else {
    var hidden = $('hidden' + divId);
    $('buyFormHidden').removeChild(hidden);
    $(divId).style.background = '#0000ff';
    var list = $('buyList').innerHTML;
    list = list.replace(",&nbsp;" + coords, "");
    list = list.replace(coords, "");
    $('buyList').innerHTML = list;
  }
  return false;
}

</g:javascript>


  <table>
    <tr>
      <td style="width: 50px">Buy:</td>
      <td><div id="buyList"></div></td>
    </tr>
    <tr>
      <td colspan="2" align="right">
        <g:form action="buildRoads">
          <span id="buyFormHidden"></span>
          <g:submitButton name="buy" value="OK"/>
        </g:form>
      </td>
    </tr>
  </table>


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
        def road = building && building.type?.dirname.indexOf('road') > -1

        def height = viewConfig.field.y
        def width = viewConfig.field.x

        def bottom = (i * viewConfig.field.y) + viewConfig.canvas.y / 2 - viewConfig.field.y
        def left = (j * viewConfig.field.x) - viewConfig.field.x + viewConfig.canvas.x / 2
      %>

        <a href="#" onclick="road(${position.x + j},${position.y + i}); return false;">
          <div id="${position.x + j};${position.y + i}"
                  style="position:absolute; float: left;
                  background-color: ${road? '#ff0000': '#0000ff'};
                  bottom: ${bottom}px;
                  left:${left}px;
                  width: ${width}px;
                  height: ${height}px;
                  z-index: 0;">
          </div>
        </a>

    </g:each>
  </g:each>
 </div>



</body>
</html>
