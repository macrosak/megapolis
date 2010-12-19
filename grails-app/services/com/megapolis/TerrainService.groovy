package com.megapolis

import com.megapolis.game.Field
import com.megapolis.game.Building

class TerrainService {

    static transactional = true

    def generateGrass() {
        def background = Building.findByDirname('background')
        def rainbow = Building.findByDirname('skyscraper-rainbow')
        def black = Building.findByDirname('skyscraper-black')
        def office = Building.findByDirname('office1')
        def road = Building.findByDirname('road-upleft')

        Field.list().each {
            it.delete()
        }

        (-30..30).each { x ->
            (-30..30).each { y ->
//                if (x == 0 && y == 0)
//                    new Field(coordX: x, coordY: y, building: black).save()
//                else if (x == 3 && y == 0)
//                    new Field(coordX: x, coordY: y, building: rainbow).save()
//                else if (x == 0 && y == -3)
//                    new Field(coordX: x, coordY: y, building: office).save()
//                else if (y == -2) {
//                    if(x % 2 == 0)
//                        new Field(coordX: x, coordY: y, building: road).save()
//                }
//                else if (y == -1) {}
//                else
                    new Field(coordX: x, coordY: y, price: 100).save()
            }
        }
    }
}
