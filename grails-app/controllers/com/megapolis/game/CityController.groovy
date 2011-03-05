package com.megapolis.game

import com.megapolis.FacebookController
import org.codehaus.groovy.grails.orm.hibernate.validation.UniqueConstraint

class CityController extends FacebookController {
    def terrainService
    def facebookService
    def cityService

    def index = { redirect action: 'show' }

    def show = {
        def player =  facebookService.player
        def x, y
        (x, y) = cityService.position(params.posX, params.posY)
        def viewConfig = grailsApplication.config.views.cityLarge
        def fields = cityService.getCityFields(x, y, viewConfig)
        [player: player, fields: fields, viewConfig: viewConfig, position: [x: x, y: y]]
    }

    def showSmall = {
        def player =  facebookService.player
        def x, y
        (x, y) = cityService.position(params.posX, params.posY)
        def viewConfig = grailsApplication.config.views.cityMedium
        def fields = cityService.getCityFields(x, y, viewConfig)
        [player: player, fields: fields, viewConfig: viewConfig, position: [x: x, y: y]]
    }

    def buyBuild = {
        def player =  facebookService.player
        def x, y
        (x, y) = cityService.position(params.posX, params.posY)
        def viewConfig = grailsApplication.config.views.buy
        def fields = cityService.getCityFields(x, y, viewConfig)
        [player: player, fields: fields, viewConfig: viewConfig, position: [x: x, y: y]]
    }

    def roads = {
        def fields = cityService.getCityFields(0, 0, grailsApplication.config.views.roads)
        [fields: fields, viewConfig: grailsApplication.config.views.roads, position: [x: 0, y: 0]]
    }

    def buy = {
        def player =  facebookService.player
        def fieldsDesc = params.list('field')
        def fields = [] as Set
        fieldsDesc.each {
            def coord = it.split(';')
            fields << Field.findByCoordXAndCoordY(coord[0].toInteger(), coord[1].toInteger())
        }

        fields.each { Field f ->
            f.owner = player
            f.save()
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

        def we = BuildingType.findByDirname('road-upleft')
        def ns = BuildingType.findByDirname('road-upright')
        def ne = BuildingType.findByDirname('road-north-east')
        def nw = BuildingType.findByDirname('road-north-west')
        def se = BuildingType.findByDirname('road-south-east')
        def sw = BuildingType.findByDirname('road-south-west')
        def cr = BuildingType.findByDirname('crossroad')
        def tn = BuildingType.findByDirname('Tcrossroad-north')
        def tw = BuildingType.findByDirname('Tcrossroad-west')
        def ts = BuildingType.findByDirname('Tcrossroad-south')
        def te = BuildingType.findByDirname('Tcrossroad-east')

        fields.each { Field road ->
            def newRoadEast = fields.find { it.coordX == road.coordX + 1 && it.coordY == road.coordY}
            def oldRoadEast = Field.findByCoordXAndCoordY(road.coordX + 1, road.coordY)
            def roadEast = (newRoadEast || oldRoadEast?.building?.type?.dirname?.indexOf('road') > -1) ? 1 : 0

            def newRoadWest = fields.find { it.coordX == road.coordX - 1 && it.coordY == road.coordY}
            def oldRoadWest = Field.findByCoordXAndCoordY(road.coordX - 1, road.coordY)
            def roadWest = (newRoadWest || oldRoadWest?.building?.type?.dirname?.indexOf('road') > -1) ? 1 : 0

            def newRoadNorth = fields.find { it.coordX == road.coordX && it.coordY == road.coordY + 1}
            def oldRoadNorth = Field.findByCoordXAndCoordY(road.coordX, road.coordY + 1)
            def roadNorth = (newRoadNorth || oldRoadNorth?.building?.type?.dirname?.indexOf('road') > -1) ? 1 : 0

            def newRoadSouth = fields.find { it.coordX == road.coordX && it.coordY == road.coordY - 1}
            def oldRoadSouth = Field.findByCoordXAndCoordY(road.coordX, road.coordY - 1)
            def roadSouth = (newRoadSouth || oldRoadSouth?.building?.type?.dirname?.indexOf('road') > -1) ? 1 : 0

            if(roadEast + roadWest + roadNorth + roadSouth == 4)
                road.building = new Building(type: cr)
            else if(roadEast + roadWest == 2)
                road.building = new Building(type: we)
            else if(roadNorth + roadSouth == 2)
                road.building = new Building(type: ns)
            else if(roadNorth + roadEast == 2)
                road.building = new Building(type: ne)
            else if(roadNorth + roadWest == 2)
                road.building = new Building(type: nw)
            else if(roadSouth + roadEast == 2)
                road.building = new Building(type: se)
            else if(roadSouth + roadWest == 2)
                road.building = new Building(type: sw)
            else if(roadSouth + roadWest + roadEast == 3)
                road.building = new Building(type: ts)
            else if(roadSouth + roadNorth + roadEast == 3)
                road.building = new Building(type: te)
            else if(roadNorth + roadWest + roadEast == 3)
                road.building = new Building(type: tn)
            else if(roadSouth + roadWest + roadNorth == 3)
                road.building = new Building(type: tw)
            road.save(flush: true)

        }
        redirect(action: 'buyBuild')

    }

    def build = {
        def coord = params.field.split(';')
        def x = coord[0].toInteger()
        def y = coord[1].toInteger()
        def field = Field.findByCoordXAndCoordY(x, y)
        def building = new Building(type: BuildingType.get(params.buildingId))
        building.init()
        field.building = building
        field.save(flush:true)
        redirect(action: 'show')
    }

    def repair = {
        redirect(action: 'show')
    }

    def generate = {
        terrainService.generateGrass()
        redirect(action: 'show')
    }

    def flushSession = {
        facebookService.accessToken = null
        facebookService.me = null
        facebookService.meMap = null
    }

}
