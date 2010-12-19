package com.megapolis.game

import com.megapolis.FacebookController

class CityController extends FacebookController {
    def terrainService
    def facebookService
    def cityService

    def newShow = {}

    def profile = {}

    def index = { redirect action: 'show' }

    def show = {
        def player =  facebookService.player
        def x, y
        (x, y) = cityService.position(params.posX, params.posY)
        def fields = cityService.getCityFields(x, y, grailsApplication.config.city.view)
        [player: player, fields: fields, viewConfig: grailsApplication.config.city.view, position: [x: x, y: y]]
    }

    def showSmall = {
         def player =  facebookService.player
        def x, y
        (x, y) = cityService.position(params.posX, params.posY)
        def fields = cityService.getCityFields(x, y, grailsApplication.config.city.viewSmall)
        [player: player, fields: fields, viewConfig: grailsApplication.config.city.viewSmall, position: [x: x, y: y]]
    }

    def buyBuild = {
        def player =  facebookService.player
        def fields = cityService.getCityFields(player.homeX, player.homeY, grailsApplication.config.city.buy)
        [player: player, fields: fields, viewConfig: grailsApplication.config.city.buy, position: [x: player.homeX, y: player.homeY]]
    }

    def roads = {
        def fields = cityService.getCityFields(0, 0, grailsApplication.config.city.roads)
        [fields: fields, viewConfig: grailsApplication.config.city.roads, position: [x: 0, y: 0]]
    }

    def buy = {
        def player =  facebookService.player
        def fieldsDesc = params.list('field')
        def fields = [] as Set
        fieldsDesc.each {
            def coord = it.split(';')
            fields << Field.findByCoordXAndCoordY(coord[0].toInteger(), coord[1].toInteger())
        }

        fields.each {
            it.owner = player
            it.save()
        }

        player.save(flush: true)
        redirect(action: 'buyBuild')

    }

    def buildRoads = {
        def fieldsDesc = params.list('field')
        def fields = [] as Set
        fieldsDesc.each {
            def coord = it.split(';')
            fields << Field.findByCoordXAndCoordY(coord[0].toInteger(), coord[1].toInteger())
        }

        def we = Building.findByDirname('road-upleft')
        def ns = Building.findByDirname('road-upright')
        def ne = Building.findByDirname('road-north-east')
        def nw = Building.findByDirname('road-north-west')
        def se = Building.findByDirname('road-south-east')
        def sw = Building.findByDirname('road-south-west')
        def cr = Building.findByDirname('crossroad')

        fields.each { road ->
            def newRoadEast = fields.find { it.coordX == road.coordX + 2 && it.coordY == road.coordY}
            def oldRoadEast = Field.findByCoordXAndCoordY(road.coordX + 2, road.coordY)
            def roadEast = (newRoadEast || oldRoadEast?.building?.dirname.indexOf('road') > -1) ? 1 : 0

            def newRoadWest = fields.find { it.coordX == road.coordX - 2 && it.coordY == road.coordY}
            def oldRoadWest = Field.findByCoordXAndCoordY(road.coordX - 2, road.coordY)
            def roadWest = (newRoadWest || oldRoadWest?.building?.dirname.indexOf('road') > -1) ? 1 : 0

            def newRoadNorth = fields.find { it.coordX == road.coordX && it.coordY == road.coordY + 2}
            def oldRoadNorth = Field.findByCoordXAndCoordY(road.coordX, road.coordY + 2)
            def roadNorth = (newRoadNorth || oldRoadNorth?.building?.dirname.indexOf('road') > -1) ? 1 : 0

            def newRoadSouth = fields.find { it.coordX == road.coordX && it.coordY == road.coordY - 2}
            def oldRoadSouth = Field.findByCoordXAndCoordY(road.coordX, road.coordY - 2)
            def roadSouth = (newRoadSouth || oldRoadSouth?.building?.dirname.indexOf('road') > -1) ? 1 : 0

            if(roadEast + roadWest + roadNorth + roadSouth > 2)
                road.building = cr
            else if(roadEast + roadWest == 2)
                road.building = we
            else if(roadNorth + roadSouth == 2)
                road.building = ns
            else if(roadNorth + roadEast == 2)
                road.building = ne
            else if(roadNorth + roadWest == 2)
                road.building = nw
            else if(roadSouth + roadEast == 2)
                road.building = se
            else if(roadSouth + roadWest == 2)
                road.building = sw

            road.save(flush: true)
        }
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
