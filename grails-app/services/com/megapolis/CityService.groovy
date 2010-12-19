package com.megapolis

import com.megapolis.game.Field
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class CityService {

    def getCityFields(x, y, config) {
        int width = config.fields.x
        int height = config.fields.y
        Field.createCriteria().list {
            and {
                ge('coordX', x - width)
                le('coordX', x + width)
                ge('coordY', y - height)
                le('coordY', y + height)
                order('coordY', 'asc')
                order('coordX', 'asc')
            }
        }
    }
}