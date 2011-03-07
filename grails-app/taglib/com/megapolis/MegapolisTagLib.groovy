package com.megapolis


class MegapolisTagLib {

    def klikatko ={attrs ->
        def position=attrs.position

        out << """<img style="position:absolute; z-index:1000" id="klikatko" src="${resource( dir:'images', file: 'mapcontrols.png')}" usemap="#_klikatko" border="0" width="59" height="59" alt="" />
        <map id="_klikatko" name="_klikatko">
        <area shape="rect" coords="19,34,37,52" href="${createLink(action:'show', params:[posX: position.x + 1, posY: position.y - 1])}" alt="" title=""    />
        <area shape="rect" coords="35,18,53,36" href="${createLink(action:'show', params:[posX: position.x + 1, posY: position.y + 1])}" alt="" title=""    />
        <area shape="rect" coords="19,1,37,19" href="${createLink(action:'show', params:[posX: position.x - 1, posY: position.y + 1])}" alt="" title=""    />
        <area shape="rect" coords="3,18,21,36" href="${createLink(action:'show', params:[posX: position.x - 1, posY: position.y - 1])}" alt="" title=""    />
        <area shape="rect" coords="19,18,37,35" href="${createLink(action:'showSmall')}" alt="" title=""    />
        </map>"""
    }

    def cityField = { attrs ->
        def i=attrs.i
        def j=attrs.j
        def fields=attrs.fields
        def viewConfig=attrs.viewConfig
        def position=attrs.position
        def zindex=attrs.zindex
        def type=attrs.type ?: 'large'


        def field = fields.find { field ->
            field.coordX == position.x + j && field.coordY == position.y +i}

        def building = field?.building
        def image = building?.type?."$type"

        def height = image?.height ?: viewConfig.field.y
        def width = image?.width ?: viewConfig.field.x

        def bottom = ((i - j) * viewConfig.field.y / 2) + viewConfig.canvas.y / 2 - viewConfig.field.y / 2
        def left = ((j + i) * viewConfig.field.x / 2) - viewConfig.field.x / 2 + viewConfig.canvas.x / 2

        bottom += image?.offsetY ?: 0
        left -= image?.offsetX ?: 0
        left += viewConfig.field.x / 2


      if (image){
        out << """<div id='${position.x + j};${position.y + i}'
        style='position:absolute; float: left;
        bottom: ${bottom}px;
        left:${left}px;
        width: ${width}px;
        height: ${height}px;
        z-index: ${zindex};'>
        <img alt='buildingID = [${building?.id}, ${building?.type?.id}, ${building?.type?.dirname}]' src='${resource(dir: 'images/buildings/' + building.type.dirname, file: image.filename)}'/>
        </div>"""
      }
    }

}
