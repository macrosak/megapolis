package com.megapolis

import com.megapolis.game.Field
import com.megapolis.game.Building
import com.megapolis.game.BuildingType

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
            it.delete()
        }

        double theta = Math.PI/4;
        def R = [Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta)] as double[];

        (-30..30).each { x ->
            (-30..30).each { y ->
                def b = null
                if (x == 0 && y == 0)
                    b = new Building(type: BuildingType.findByDirname('road-upleft'))

                b?.init()
                double rx = R[0] * x + R[1] * y;
                double ry = R[2] * x + R[3] * y;

                new Field(coordX: x, coordY: y, rx: rx, ry: ry, building: b).save()

            }
        }
    }
}
