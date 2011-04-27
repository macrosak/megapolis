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

                b?.init()
                new Field(coordX: x, coordY: y, rx: rx, ry: ry, building: b).save(flush: true)

            }
        }
    }
}
