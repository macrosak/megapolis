package com.megapolis.game

import com.megapolis.FacebookController
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class BuildingController extends FacebookController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def withdraw = {
        def building = Building.get(params.remove('id'))
        building.withdraw()
        redirect(controller: 'player', action: 'myBuildings')
    }
}
