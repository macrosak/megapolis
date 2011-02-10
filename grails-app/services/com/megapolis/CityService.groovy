package com.megapolis

import com.megapolis.game.Field
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class CityService {

    static scope = "session"

    int posX = 0, posY = 0

    def position(x, y) {
        if(x)
            posX = x.toInteger()
        if(y)
            posY = y.toInteger()
        return [posX, posY]
    }

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

    int lucrativity(Field field) {

    }

    int population(Field field, int range) {

    }

    int neededShopCostumers(Field field, int range) {

    }
}
