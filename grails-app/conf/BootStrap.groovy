import com.megapolis.game.Field
import com.megapolis.game.Building
import com.megapolis.game.Image
import com.megapolis.game.BuildingType
import com.megapolis.game.Residential
import com.megapolis.game.Shop
import com.megapolis.game.Ground

class BootStrap {
    def terrainService

    def init = { servletContext ->

        def house1 = new Residential(name: 'Luxury Residence', dirname: 'house1', price: 1000,
                lucrativity: 10, profitTime: 30, maxResidents: 20,
                large: new Image(filename: 'iso.png', height: 184, width: 258, offsetX: 129, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height:91, width:128, offsetX:64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height:46, width:64, offsetX:32, offsetY: -1).save()).save(failOnError: true)
        def shop = new Shop(name: 'City Mall', dirname: 'office2', price: 3000,
                lucrativity: 30, profitTime: 60, salesRange: 2, maxProfit: 500, idealCustomersCount: 200,
                large: new Image(filename: 'iso.png', height: 350, width: 258, offsetX: 129, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 174, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 87, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)

        def roadWE = new Ground(name: 'Road', dirname: 'road-upleft', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNS = new Ground(name: 'Road', dirname: 'road-upright', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNE = new Ground(name: 'Road', dirname: 'road-north-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadNW = new Ground(name: 'Road', dirname: 'road-north-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadSE = new Ground(name: 'Road', dirname: 'road-south-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadSW = new Ground(name: 'Road', dirname: 'road-south-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadCR = new Ground(name: 'Road', dirname: 'crossroad', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTN = new Ground(name: 'Road', dirname: 'Tcrossroad-north', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTE = new Ground(name: 'Road', dirname: 'Tcrossroad-east', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTS = new Ground(name: 'Road', dirname: 'Tcrossroad-south', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def roadTW = new Ground(name: 'Road', dirname: 'Tcrossroad-west', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        def background = new Ground(name: 'Background', dirname: 'background', price: 1, ground: true,
                large: new Image(filename: 'iso.png', height: 130, width: 256, offsetX: 128, offsetY: -1).save(),
                medium: new Image(filename: 'iso_medium.png', height: 65, width: 128, offsetX: 64, offsetY: -1).save(),
                small: new Image(filename: 'iso_small.png', height: 33, width: 64, offsetX: 32, offsetY: -1).save()).save(failOnError: true)
        terrainService.generateGrass()

    }
    def destroy = {
    }
}
