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

import com.megapolis.game.Field
import com.megapolis.game.Building
import com.megapolis.game.BuildingType
import com.megapolis.game.player.Player

class TerrainService {

    static transactional = true

    def generateGrass() {
//        def background = Building.findByDirname('background')
//        def rainbow = Building.findByDirname('skyscraper-rainbow')
//        def black = Building.findByDirname('skyscraper-black')
//        def shops = Building.findByDirname('small-shop')
//        def blue = Building.findByDirname('skyscraper-blue')
//        def office = Building.findByDirname('office1')
//        def road = Building.findByDirname('road-upleft')

        Field.list().each {
            it.delete(flush: true)
        }

        double theta = Math.PI/4;
        def R = [Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta)] as double[];

        (-30..30).each { x ->
            (-30..30).each { y ->
                def b = null
                double rx = R[0] * x + R[1] * y;
                double ry = R[2] * x + R[3] * y;
                if (x == 0 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 1 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 2 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('Tcrossroad-south'))
                else if (x == 3 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 4 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == -1 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('crossroad'))
                else if (x == -1 && y == 1)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == 2)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == 3)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == 4)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == 5)
                    b = new Building(type: BuildingType.findByDirname('road-south-west'))
                else if (x == -2 && y == 5)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == -3 && y == 5)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == -1 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == -2)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == -3)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -1 && y == -4)
                    b = new Building(type: BuildingType.findByDirname('road-north-east'))
                else if (x == 0 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 1 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 2 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('Tcrossroad-north'))
                else if (x == 3 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 4 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == 5 && y ==-4 )
                    b = new Building(type: BuildingType.findByDirname('road-north-west'))
                else if (x == 5 && y == -3)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == 5 && y == -2)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == 5 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == 5 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-south-west'))
                else if (x == 2 && y == -3)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == 2 && y == -2)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == 2 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -2 && y ==0 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == -3 && y ==0 )
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))
                else if (x == -4 && y ==0 )
                    b = new Building(type: BuildingType.findByDirname('road-north-east'))
                else if (x == -4 && y == 1)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -4 && y == 2)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -4 && y == 3)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -4 && y == 4)
                    b = new Building(type: BuildingType.findByDirname('road-upright'))
                else if (x == -4 && y == 5)
                    b = new Building(type: BuildingType.findByDirname('road-south-east'))
                else if (x == 0 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('house1'))
                else if (x == -2 && y == 4)
                    b = new Building(type: BuildingType.findByDirname('house1'))
                else if (x == -2 && y == 1)
                    b = new Building(type: BuildingType.findByDirname('house2'))
                else if (x == 1 && y == -3)
                    b = new Building(type: BuildingType.findByDirname('house3'))
                else if (x == 1 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                else if (x == -3 && y == 4)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                else if (x == -2 && y == 2)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                else if (x == 3 && y == -3)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                else if (x == 4 && y == -1)
                    b = new Building(type: BuildingType.findByDirname('house3'))
                else if (x == 3 && y == -2)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                else if (x == -3 && y == 3)
                    b = new Building(type: BuildingType.findByDirname('office2'))
                b?.init()

                def owner = null
                if(b)
                    owner = Player.findByFacebookId(1678623744)
//                    owner = Player.findByFacebookId(-1)

                new Field(owner: owner, coordX: x, coordY: y, rx: rx, ry: ry, building: b).save(flush: true)
            }
        }
    }
}
