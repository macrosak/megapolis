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

package com.megapolis.game

import com.megapolis.FacebookController
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass

class BuildingController extends FacebookController {

    def facebookService

    def index = {
        redirect(action: "list", params: params)
    }

    def withdraw = {
        def building = Building.get(params.remove('id'))
        if(facebookService.player == building.owner) {
            def profit = building.withdraw()
            if(profit >= 0)
                flash.message = "You have earned \$$profit."
            else
                flash.message = "You cannot withdraw money yet. Try it later."
        }
        redirect(controller: 'city', action: 'show')
    }

    def delete = {
        def building = Building.get(params.remove('id'))
        if(facebookService.player == building.owner) {
            building.field.building = null
            def name = building.type.name
            building.delete(flush: true)
            flash.message = "You have demolished building $name."
        }
        redirect(controller: 'city', action: 'show')
    }
}
