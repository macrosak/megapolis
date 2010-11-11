package com.megapolis

import com.megapolis.game.Field
import com.megapolis.game.Building

class TerrainService {

    static transactional = true

    def generateGrass() {
        def grass = Building.findByFilename('grass.png')

        (-20..20).each { x ->
            (-20..20).each { y ->
                new Field(coordX: x, coordY: y, building: grass).save()
            }
        }
    }
}
