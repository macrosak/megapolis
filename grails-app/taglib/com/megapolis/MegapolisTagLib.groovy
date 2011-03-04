package com.megapolis


class MegapolisTagLib {

    /**
     * @param building
     * @param x
     * @param y
     * @param viewConfig
     * @param image (optional, default large)
     */

    def cityField = { attrs, body ->
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
