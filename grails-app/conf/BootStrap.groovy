import com.megapolis.game.Field
import com.megapolis.game.Building
import com.megapolis.game.Image

class BootStrap {
    def terrainService

    def init = { servletContext ->
//        def small = new Building(filename: 'small.png', height: 191, price: 10000).save(failOnError: true)
//        def medium = new Building(filename: 'medium.png', height: 256, price: 50000).save(failOnError: true)
//        def high = new Building(filename: 'high.png', height: 344, price: 150000).save(failOnError: true)
//        def rl = new Building(filename: 'road_left.png', height: 39, price: 1).save(failOnError: true)
//        def rr = new Building(filename: 'road_right.png', height: 39, price: 1).save(failOnError: true)
//        def grass = new Building(filename: 'grass.png', height: 39, price: 1).save(failOnError: true)
//        def back = new Building(filename: 'background', height: 80, width: 160, price: 1, ground: true, offsetX: 80).save(failOnError: true)
//        def rainbow = new Building(filename: 'skyscraper-rainbow', height: 598, width: 194, price: 1, offsetX: 119).save(failOnError: true)
//        def black = new Building(filename: 'skyscraper-black', height: 833, width: 244, offsetX: 123, price: 1).save(failOnError: true)
//        def office = new Building(filename: 'office1', height: 278, width: 171, price: 1, offsetX: 94).save(failOnError: true)
//        def road = new Building(filename: 'road-upleft', height: 160, width: 320, price: 1, ground: true, offsetX: 160).save(failOnError: true)

        def back = new Building(dirname: 'background', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 80, width: 160, offsetX: 80).save(),
                top: new Image(filename: 'top.png', height: 50, width: 50).save()).save(failOnError: true)
        def rainbow = new Building(dirname: 'skyscraper-rainbow', price: 15000,
                income : 1000, name: 'Rainbow Corp. HQ',
                iso: new Image(filename: 'iso.png', height: 598, width: 194, offsetX: 119).save(),
                top: new Image(filename: 'top.png', height: 50, width: 100).save()).save(failOnError: true)
        def black = new Building(dirname: 'skyscraper-black', price: 20000,
                income : 2500, name: 'OfficeBox International',
                iso: new Image(filename: 'iso.png', height: 833, width: 244, offsetX: 123).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def office = new Building(dirname: 'office1', price: 3000,
                income : 300, name: 'OfficeBox Local Department',
                iso: new Image(filename: 'iso.png', height: 278, width: 171, offsetX: 94).save(),
                top: new Image(filename: 'top.png', height: 50, width: 100).save()).save(failOnError: true)
        def factory = new Building(dirname: 'factory', price: 9000,
                income : 700, name: 'Pudding Factory',
                iso: new Image(filename: 'iso.png', height: 293, width: 388, offsetX: 239).save(),
                top: new Image(filename: 'top.png', height: 100, width: 150).save()).save(failOnError: true)
        def blue = new Building(dirname: 'skyscraper-blue', price: 19000,
                income : 2300, name: 'Blue Sky Company',
                iso: new Image(filename: 'iso.png', height: 802, width: 223, offsetX: 156).save(),
                top: new Image(filename: 'top.png', height: 100, width: 150).save()).save(failOnError: true)

        def roadWE = new Building(dirname: 'road-upleft', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadNS = new Building(dirname: 'road-upright', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadNE = new Building(dirname: 'road-north-east', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadNW = new Building(dirname: 'road-north-west', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadSE = new Building(dirname: 'road-south-east', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadSW = new Building(dirname: 'road-south-west', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        def roadCR = new Building(dirname: 'crossroad', price: 0, ground: true,
                iso: new Image(filename: 'iso.png', height: 160, width: 320, offsetX: 160).save(),
                top: new Image(filename: 'top.png', height: 100, width: 100).save()).save(failOnError: true)
        terrainService.generateGrass()

    }
    def destroy = {
    }
}
