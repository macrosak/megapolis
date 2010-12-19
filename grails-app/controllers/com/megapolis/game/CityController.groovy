package com.megapolis.game

import com.megapolis.FacebookController

class CityController extends FacebookController {
    def terrainService
    def facebookService
    def cityService

    def index = { redirect action: 'show' }

    def show = {
        def player =  facebookService.player
        def fields = cityService.getCityFields(player.homeX, player.homeY, grailsApplication.config.city.view)
        [player: player, fields: fields, viewConfig: grailsApplication.config.city.view, position: [x: player.homeX, y: player.homeY]]
    }

    def buyBuild = {
        def player =  facebookService.player
        def fields = cityService.getCityFields(player.homeX, player.homeY, grailsApplication.config.city.buy)
        [player: player, fields: fields, viewConfig: grailsApplication.config.city.buy, position: [x: player.homeX, y: player.homeY]]
    }

    def buy = {
        def player =  facebookService.player
        def fieldsDesc = params.list('field')
        def fields = [] as Set
        fieldsDesc.each {
            def coord = it.split(';')
            fields << Field.findByCoordXAndCoordY(coord[0].toInteger(), coord[1].toInteger())
        }

        println fields

        fields.each {
            it.owner = player
            it.save()
        }

        player.save(flush: true)
        redirect(action: 'buyBuild')

    }

    def build = {
        def coord = params.field.split(';')
        def x = coord[0].toInteger()
        def y = coord[1].toInteger()
        Field.findByCoordXAndCoordY(x, y)?.delete()
        new Field(coordX: x, coordY: y, building: Building.get(params.buildingId)).save()
        redirect(action: 'show')
    }

    def generate = {
        terrainService.generateGrass()
        redirect actionName: show
        return

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
                if (y == road)
                    field.building = rl
                else if (y == road + 1)
                    field.building = rr
                else switch (random.nextInt(7)) {
                        case 0: field.building = small; break;
                        case 1: field.building = medium; break;
                        case 2: field.building = high; break;
                        default: field.building = grass
                    }
                field.save(flush: true)
            }
        }
        redirect(action: 'show')
    }

}
