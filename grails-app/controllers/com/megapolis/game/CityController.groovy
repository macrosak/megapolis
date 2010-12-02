package com.megapolis.game

import com.megapolis.FacebookController

class CityController extends FacebookController {
    def terrainService
    def facebookService

    def index = { redirect action: show}

    def show = {
      [profile: facebookService.profile]
    }

    def generate = {
        def small = Building.findByFilename('small.png')
        def medium = Building.findByFilename('medium.png')
        def high = Building.findByFilename('high.png')
        def rl = Building.findByFilename('road_left.png')
        def rr = Building.findByFilename('road_right.png')
        def grass = Building.findByFilename('grass.png')
        Field.executeUpdate("delete Field f")
        def random = new Random()
        def road = random.nextInt(11)
        (0..12).each { x ->
            (-3..12).each { y ->
                def field = new Field(coordX: x, coordY: y)
                if(y == road)
                    field.building = rl
                else if(y == road + 1)
                    field.building = rr
                else switch(random.nextInt(7)) {
                    case 0: field.building = small; break;
                    case 1: field.building = medium; break;
                    case 2: field.building = high; break;
                    default: field.building = grass
                }
                field.save(flush: true)
            }
        }
        redirect actionName: show
    }

}
