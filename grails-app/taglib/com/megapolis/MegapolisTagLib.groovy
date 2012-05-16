/*
 * Copyright 2010-2011 by MegapolisTeam
 *
 * This file is part of Megapolis.
 *
 * Megapolis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Megapolis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Megapolis.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.megapolis

import com.megapolis.game.BuildingType
import com.megapolis.game.Ground


class MegapolisTagLib {

    def facebookService

    def klikatko = {attrs ->
        def position = attrs.position
        def offset = 300
        def time = 200
//      ${createLink(action: 'show', params: [posX: position.x + 1, posY: position.y - 1])}
        out << """<img style="position:absolute; z-index:1000" id="klikatko" src="${resource(dir: 'images', file: 'mapcontrols.png')}" usemap="#_klikatko" border="0" width="59" height="59" alt="" />
        <map id="_klikatko" name="_klikatko">
        <area shape="rect" coords="19,34,37,52" href="#" onclick="jQuery('#canvas').animate({top: '-=${offset}'}, ${time})"/>
        <area shape="rect" coords="35,18,53,36" href="#" onclick="jQuery('#canvas').animate({left: '-=${offset}'}, ${time})"/>
        <area shape="rect" coords="19,1,37,19" href="#" onclick="jQuery('#canvas').animate({top: '+=${offset}'}, ${time})"/>
        <area shape="rect" coords="3,18,21,36" href="#" onclick="jQuery('#canvas').animate({left: '+=${offset}'}, ${time})"/>"""
        if(attrs.zoomAction)
            out << """<area shape="rect" coords="19,18,37,35" href="${createLink(action: attrs.zoomAction, params: attrs.zoomParams)}" alt="" title=""> """
        out << "</map>"
    }

    def profilePicture = { vrata ->
        def player = vrata.player
        if (player?.facebookId && player?.facebookId > 0) {
            out << """<img src="${facebookService.getProfilePicture(player?.facebookId ?: 0)}"/>"""
        }
    }

    def cityField = { attrs ->
        def i = attrs.i
        def j = attrs.j
        def fields = attrs.fields
        def viewConfig = attrs.viewConfig
        def position = attrs.position
        def zindex = attrs.zindex
        def type = attrs.type ?: 'large'
        def background = attrs.background ?: BuildingType.findByNameIlike('background')


        def x = position.x + j
        def y = position.y + i
        def field = fields.find { field ->
            field.coordX == position.x + j && field.coordY == position.y + i
        }

        def building = field?.building
        def buildingType = building?.type
        if(!building && field) {
            buildingType = background
        }
        def image = buildingType?."$type"

        def height = image?.height ?: viewConfig.field.y
        def width = image?.width ?: viewConfig.field.x

//        def bottom = ((i - j) * viewConfig.field.y / 2) + viewConfig.canvas.y / 2 - viewConfig.field.y / 2
//        def left = ((j + i) * viewConfig.field.x / 2) - viewConfig.field.x / 2 + viewConfig.canvas.x / 2

        def bottom = ((y - x) * viewConfig.field.y / 2) + viewConfig.canvas.y / 2 - viewConfig.field.y / 2
        def left = ((x + y) * viewConfig.field.x / 2) - viewConfig.field.x / 2 + viewConfig.canvas.x / 2

        bottom += image?.offsetY ?: 0
        left -= image?.offsetX ?: 0
        left += viewConfig.field.x / 2

        def imgClass = building && !building.type.instanceOf(Ground) && building.owner != attrs.player ? 'otherBuilding' : ''

        if (image) {

            String coord = ""
            int kLast = image.mapx?.size()?:0
            for (int k=0; k<kLast; k++) {
                coord += image.mapx[k] + ',' + image.mapy[k] + (k==kLast ? '' : ',');
            }

            out << """<div id='${position.x + j};${position.y + i}||${field?.coordX};${field?.coordY}'
        class="tile" style='position:absolute; float: left;
        bottom: ${bottom}px;
        left:${left}px;
        width: ${width}px;
        height: ${height}px;
        z-index: ${zindex};'>
        <img style='position: absolute; bottom: 0px; left: 0px; z-index: ${zindex}' class='${imgClass}'
        alt='buildingID = [${building?.id}, ${building?.type?.id}, ${building?.type?.dirname}]'
        src='${resource(dir: 'images/buildings/' + buildingType.dirname, file: image.filename)}'
        usemap="${building ? "#_building${building?.id}" : ""}"/>
        """

        if(building && !building.type.instanceOf(Ground) )
            out << """<img style='position: absolute; bottom: 0px; left: 0px; z-index: 1;' src='${resource(dir: 'images/buildings/' + background.dirname, file: background?."$type"?.filename)}'/>"""

        if(building) {
            out << """<map name="_building${building?.id}">
            <area shape="poly" coords="${coord}" href="#" alt="" title="" id="${building.id}" class="buildingMap"/>
            </map>"""

            if (building?.owner?.facebookId && building?.owner?.facebookId > 0 && !building?.type?.instanceOf(Ground)) {

               out << """ <img src="${facebookService.getProfilePicture(building?.owner?.facebookId ?: 0)}" alt="${building?.owner?.facebookId}"
                            style="position: absolute;  left: ${width/2-25}px" class="profilePicture"/> """

            }
        }

        out << "</div>"
        }
//        else if (background) {
//            out << """<div id='${position.x + j};${position.y + i}||${field?.coordX};${field?.coordY}'
//        style='position:absolute; float: left;
//        bottom: ${bottom}px;
//        left:${left}px;
//        width: ${width}px;
//        height: ${height}px;
//        z-index: 1;'>
//        <img src='${resource(dir: 'images/buildings/' + background.dirname, file: background."$type".filename)}'/>
//        </div>"""
//        }
    }

}
