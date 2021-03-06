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

import com.megapolis.game.Field
import com.megapolis.game.Building
import com.megapolis.game.Image
import com.megapolis.game.BuildingType
import com.megapolis.game.Residential
import com.megapolis.game.AdditionalBuilding
import com.megapolis.game.Shop
import com.megapolis.game.Ground
import com.megapolis.game.player.Player
import com.megapolis.PlayerService

class BootStrap {
    def terrainService

    def init = { servletContext ->
        def systemPlayer = Player.findByFacebookId(-1) ?: new Player(money: 0, facebookId: -1, profileJSON: """{"id":"-1","name":"Megapolis System","first_name":"Megapolis","last_name":"System","username":"megapolis"}""").save(failOnError: true)
        def hany = Player.findByFacebookId(1678623744) ?: new Player(facebookId: 1678623744, money: PlayerService.INITIAL_MONEY, profileJSON: """{
           "id": "1678623744",
           "name": "Martin Hanák",
           "first_name": "Martin",
           "last_name": "Hanák",
           "link": "http://www.facebook.com/profile.php?id=1678623744",
           "birthday": "06/19/1988",
           "quotes": "\\\"Peace and prosperity through superior firepower.\\\" -Colonel Quaritch",
           "gender": "male",
           "religion": "Nihilist",
           "timezone": 2,
           "locale": "en_GB",
           "verified": true,
           "updated_time": "2011-05-07T19:47:32+0000"
        }""").save(failOnError: true)

        def house1 = Residential.findByDirname('house1') ?:
            new Residential(name: 'Luxury Residence', dirname: 'house1', price: 100000,
                lucrativity: 10, profitTime: 3600, maxResidents: 15,
                large: new Image(filename: 'iso.png', height: 184, width: 258, offsetX: 129, offsetY: -1,
                    mapx: [2,36,36,59,89,106,153,171,200,223,224,259,254,195,131,128,1],
                    mapy: [107,90,50,25,21,2,2,20,26,50,99,117,123,153,184,184,121]).save(),
                medium: new Image(filename: 'iso_medium.png', height:91, width:128, offsetX:64, offsetY: -1,
                    mapx: [1,18,18,30,44,53,77,85,100,112,112,129,127,97,66,64,1],
                    mapy: [54,45,25,13,11,1,1,10,13,25,50,59,61,76,92,92,61]).save(),
                small: new Image(filename: 'iso_small.png', height:46, width:64, offsetX:32, offsetY: -1,
                    mapx: [0,9,9,15,22,26,38,43,50,56,56,65,64,49,33,32,0],
                    mapy: [27,23,13,6,5,0,1,5,6,13,25,29,31,38,46,46,30]).save()).save(failOnError: true)

        def house2 = Residential.findByDirname('house2') ?:
            new Residential(name: 'Big Residence', dirname: 'house2', price: 170000,
                lucrativity: 25, profitTime: 3600, maxResidents: 50,
                large: new Image(filename: 'iso_normal.png', height: 385, width: 256, offsetX: 128, offsetY: -1,
                    mapx: [2,36,36,59,89,106,153,171,200,223,224,259,254,195,131,128,1],
                    mapy: [107,90,50,25,21,2,2,20,26,50,99,117,123,153,184,184,121]).save(),
                medium: new Image(filename: 'iso_medium.png', height:189, width:128, offsetX:64, offsetY: -1,
                    mapx: [1,18,18,30,44,53,77,85,100,112,112,129,127,97,66,64,1],
                    mapy: [54,45,25,13,11,1,1,10,13,25,50,59,61,76,92,92,61]).save(),
                small: new Image(filename: 'iso_small.png', height:95, width:64, offsetX:32, offsetY: -1,
                    mapx: [0,9,9,15,22,26,38,43,50,56,56,65,64,49,33,32,0],
                    mapy: [27,23,13,6,5,0,1,5,6,13,25,29,31,38,46,46,30]).save()).save(failOnError: true)

        def house3 = Residential.findByDirname('house3') ?:
            new Residential(name: 'Extra Luxurious Residence', dirname: 'house3', price: 210000,
                lucrativity: 50, profitTime: 3600, maxResidents: 20,
                large: new Image(filename: 'iso_normal.png', height: 300, width: 256, offsetX: 128, offsetY: -1,
                    mapx: [2,36,36,59,89,106,153,171,200,223,224,259,254,195,131,128,1],
                    mapy: [107,90,50,25,21,2,2,20,26,50,99,117,123,153,184,184,121]).save(),
                medium: new Image(filename: 'iso_medium.png', height:150, width:128, offsetX:64, offsetY: -1,
                    mapx: [1,18,18,30,44,53,77,85,100,112,112,129,127,97,66,64,1],
                    mapy: [54,45,25,13,11,1,1,10,13,25,50,59,61,76,92,92,61]).save(),
                small: new Image(filename: 'iso_small.png', height:75, width:64, offsetX:32, offsetY: -1,
                    mapx: [0,9,9,15,22,26,38,43,50,56,56,65,64,49,33,32,0],
                    mapy: [27,23,13,6,5,0,1,5,6,13,25,29,31,38,46,46,30]).save()).save(failOnError: true)

        def shop = Shop.findByDirname('office2') ?:
            new Shop(name: 'City Mall', dirname: 'office2', price: 150000,
                lucrativity: 30, profitTime: 3600, salesRange: 3, maxProfit: 15000, idealCustomersCount: 200,
                large: new Image(filename: 'iso.png', height: 350, width: 258, offsetX: 129, offsetY: -1,
                    mapx: [2,44,45,118,155,156,215,215,258,130],
                    mapy: [286,262,35,1,20,32,62,262,286,350]).save(),
                medium: new Image(filename: 'iso_medium.png', height: 174, width: 128, offsetX: 64, offsetY: -1,
                    mapx: [1,22,23,59,78,78,108,108,129,65],
                    mapy: [143,131,17,1,10,16,31,131,143,175]).save(),
                small: new Image(filename: 'iso_small.png', height: 87, width: 64, offsetX: 32, offsetY: -1,
                    mapx: [0,11,11,30,39,39,54,54,65,32],
                    mapy: [71,65,9,0,5,8,15,66,72,88]).save()).save(failOnError: true)
        def shop1 = Shop.findByDirname('office3') ?:
            new Shop(name: 'Great City Mall', dirname: 'office3', price: 300000,
                lucrativity: 40, profitTime: 3600, salesRange: 5, maxProfit: 25000, idealCustomersCount: 500,
                large: new Image(filename: 'iso.png', height: 341, width: 258, offsetX: 129, offsetY: -1,
                    mapx: [2,44,45,118,155,156,215,215,258,130],
                    mapy: [286,262,35,1,20,32,62,262,286,350]).save(),
                medium: new Image(filename: 'iso_medium.png', height: 169, width: 128, offsetX: 64, offsetY: -1,
                    mapx: [1,22,23,59,78,78,108,108,129,65],
                    mapy: [143,131,17,1,10,16,31,131,143,175]).save(),
                small: new Image(filename: 'iso_small.png', height: 85, width: 64, offsetX: 32, offsetY: -1,
                    mapx: [0,11,11,30,39,39,54,54,65,32],
                    mapy: [71,65,9,0,5,8,15,66,72,88]).save()).save(failOnError: true)

        def statue = AdditionalBuilding.findByDirname('statue') ?:
            new AdditionalBuilding(name: 'Heroic Statue', dirname: 'statue', price:50000,
                lucrativity: 100, profitTime: 0,
                large: new Image(filename: 'iso.png', height: 150, width: 258, offsetX: 129, offsetY: -1,
                    mapx: [2,44,45,118,155,156,215,215,258,130],
                    mapy: [286,262,35,1,20,32,62,262,286,350]).save(),
                medium:new Image(filename: 'iso_medium.png', height: 75, width: 128, offsetX: 64, offsetY: -1,
                    mapx: [2,44,45,118,155,156,215,215,258,130],
                    mapy: [286,262,35,1,20,32,62,262,286,350]).save(),
                small: new Image(filename: 'iso_small.png', height: 38, width: 64, offsetX: 32, offsetY: -1,
                    mapx: [2,44,45,118,155,156,215,215,258,130],
                    mapy: [286,262,35,1,20,32,62,262,286,350]).save()).save(failOnError: true)
            
        def roadWE = Ground.findByDirname('road-upleft') ?:
            new Ground(name: 'Road', dirname: 'road-upleft', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNS = Ground.findByDirname('road-upright') ?:
            new Ground(name: 'Road', dirname: 'road-upright', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNE = Ground.findByDirname('road-north-east') ?:
            new Ground(name: 'Road', dirname: 'road-north-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNW = Ground.findByDirname('road-north-west') ?:
            new Ground(name: 'Road', dirname: 'road-north-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadSE = Ground.findByDirname('road-south-east') ?:
            new Ground(name: 'Road', dirname: 'road-south-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadSW = Ground.findByDirname('road-south-west') ?:
            new Ground(name: 'Road', dirname: 'road-south-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadCR = Ground.findByDirname('crossroad') ?:
            new Ground(name: 'Road', dirname: 'crossroad', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTN = Ground.findByDirname('Tcrossroad-north') ?:
            new Ground(name: 'Road', dirname: 'Tcrossroad-north', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTE = Ground.findByDirname('Tcrossroad-east') ?:
            new Ground(name: 'Road', dirname: 'Tcrossroad-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTS = Ground.findByDirname('Tcrossroad-south') ?:
            new Ground(name: 'Road', dirname: 'Tcrossroad-south', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTW = Ground.findByDirname('Tcrossroad-west') ?:
            new Ground(name: 'Road', dirname: 'Tcrossroad-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def background = Ground.findByDirname('background') ?:
            new Ground(name: 'Background', dirname: 'background', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        terrainService.generateGrass()

    }
    def destroy = {
    }
}
