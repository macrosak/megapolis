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

function buy(x, y, p) {
  var coords = "[" + x + "; " + y + "]"
  var divId = x + ";" + y
  if($('buyList').innerText.indexOf(coords) == -1 ) {
    $('buyFormHidden').innerHTML += "<input type='hidden' name='field' value='" + divId + "' id='hidden" + divId + "'/>";
    if($('buyList').innerText != "")
      $('buyList').innerHTML += ",&nbsp;"
    $('buyList').innerText += coords;
    $(divId).style.background = '#ffff00';
    var price = parseInt($('priceSpan').innerText);
    var money = parseInt($('moneySpan').innerText);
    price += p;
    if(money < price) {
      $('priceOuterSpan').style.color = '#ff0000';
      $('buyButton').disabled = true;
    }
    $('priceSpan').innerText = price;
  } else {
    var hidden = $('hidden' + divId);
    $('buyFormHidden').removeChild(hidden);
    $(divId).style.background = '#0000ff';
    var list = $('buyList').innerHTML;
    list = list.replace(",&nbsp;" + coords, "");
    list = list.replace(coords, "");
    $('buyList').innerHTML = list;
    var price = parseInt($('priceSpan').innerText);

    var money = parseInt($('moneySpan').innerText);
    price -= p;
    if(money >= price) {
      $('priceOuterSpan').style.color = '#000000';
      $('buyButton').disabled = false;
    }
    $('priceSpan').innerText = price;
  }
}

  function build(x, y, price) {
    $('buildCoord').value = x + ";" + y
    buildDialog.show()
  }

  function buildSubmit(id) {
    $('buildingId').value = id
    return true
  }

</g:javascript>


  <table>
    <tr>
      <td style="width: 50px">Money:</td>
      <td>$<span id="moneySpan">${player.money}</span></td>
    </tr>
    <tr>
      <td style="width: 50px">Buy:</td>
      <td><div id="buyList"></div></td>
    </tr>
    <tr>
      <td style="width: 50px">Price:</td>
      <td><span id="priceOuterSpan">$<span id="priceSpan">0</span></span></td>
    </tr>
    <tr>
      <td colspan="2" align="left">
        <g:form action="buy">
          <span id="buyFormHidden"></span>
          <g:submitButton name="buy" value="Buy land" id="buyButton"/>
        </g:form>
      </td>
    </tr>
  </table>

<g:dialog name="buildDialog" title="Build" draggable="true" width="450" height="600" resizable="true">
  <div id="buildDialogDiv" style="z-index:10000">
    <form action="build">
      <input type="hidden" name="field" value="" id="buildCoord"/>
      <input type="hidden" name="buildingId" value="" id="buildingId"/>
      <g:each in="${Building.findAllByGround(false)}" var="b">
      %{--<g:each in="${Building.list()}" var="b">--}%
      <div>
        <div>
        <h2>${b.name}</h2>
        <input type="image" src="${resource(dir: 'images/buildings/' + b.dirname, file: 'preview.png')}" onclick="buildSubmit(${b.id})"/>
        <br>
        Price: $${b.price}
        Income: <span class="income">+$${b.income}</span>
        </div>
        
      </div>
    </g:each>
    </form>

    <button onclick="buildDialog.hide()">Cancel</button>

  </div>
</g:dialog>

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
        def image = building?.top

        def height = image?.height ?: viewConfig.field.y
        def width = image?.width ?: viewConfig.field.x

        def bottom = (i * viewConfig.field.y) + viewConfig.canvas.y / 2 - viewConfig.field.y
        def left = (j * viewConfig.field.x) - viewConfig.field.x + viewConfig.canvas.x / 2

        if(image)
          left -= image.width - viewConfig.field.x
      %>
      <g:if test="${building && building.dirname != 'background'}">

        <div id="${position.x + j};${position.y + i}"
                style="position:absolute; float: left;
                bottom: ${bottom}px;
                left:${left}px;
                width: ${width}px;
                height: ${height}px;
                z-index: 1;">

          <img src="${resource(dir: 'images/buildings/' + building.dirname, file: image.filename)}"/>

        </div>
      </g:if>
      <g:elseif test="${field}">
        <%
          def action = field?.owner == null? 'buy': field?.owner == player ? 'build': 'null'
        %>
        <a href="#" onclick="${action}(${position.x + j}, ${position.y + i}, ${field?.price})">
          <div id="${position.x + j};${position.y + i}"
                  style="position:absolute; float: left;
                  background-color: ${field?.owner == null? '#0000ff': field?.owner == player ? '#00ff00': '#ff0000'};
                  bottom: ${bottom}px;
                  left:${left}px;
                  width: ${width}px;
                  height: ${height}px;
                  z-index: 0;
                  border:1px solid black;"
                  title="Price: $${field?.price}">
          </div>
        </a>
      </g:elseif>

    </g:each>
  </g:each>
 </div>



</body>
</html>
