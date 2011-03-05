package com.megapolis

import com.megapolis.game.Field
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.megapolis.game.Shop

class CityService {

    static scope = "singleton"

    public static W = 2  // Lucrativity square radius

    static magicX = 3

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
        return Field.createCriteria().list {
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

    double lucrativity(Field field) {
    
      def fields = getNearbyFields(field, W)
      
      def cat = [:]
    
      for (f in fields) {
        
        if (cat[f.building.type.class] == null)
          cat[f.building.type.class] = 0
        cat[f.building.type.class] += f.building.type.lucrativity * W / (dist(field, f)+W)

      }

      def result = 0.0

      cat.each{key,value->
          result += Math.log(value)/Math.log(magicX)
      }

      return result

    }

    int dist(Field f1, Field f2) {
        return Math.sqrt((f1.coordX-f2.coordX)**2+(f1.coordY-f2.coordY)**2)
    }

    def getNearbyFields(Field field, int range) {
      return Field.createCriteria().list {
            and {
                ge('coordX', field.coordX - range)
                le('coordX', field.coordX + range)
                ge('coordY', field.coordY - range)
                le('coordY', field.coordY + range)
                isNotNull('building')
            }
      }
    }

    int population(Field field, int range) {
        def fields = getNearbyFields(field, W)

        def result = 0

        for (f in fields) {
            result += f.building.residents
        }

        return result
    }

    int neededShopCustomers(Field field, int range) {
        def fields = getNearbyFields(field, W)

        def result = 0

        for (f in fields) {
            if(f.building.type.instanceOf(Shop))
                result += f.building.type.idealCustomersCount
        }

        return result
    }
}
