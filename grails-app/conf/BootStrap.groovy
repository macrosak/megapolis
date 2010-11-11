import com.megapolis.game.Field
import com.megapolis.game.Building

class BootStrap {
    def terrainService

    def init = { servletContext ->
        def small = new Building(filename: 'small.png', height: 191, price: 10000).save(failOnError: true)
        def medium = new Building(filename: 'medium.png', height: 256, price: 50000).save(failOnError: true)
        def high = new Building(filename: 'high.png', height: 344, price: 150000).save(failOnError: true)
        def rl = new Building(filename: 'road_left.png', height: 39, price: 1).save(failOnError: true)
        def rr = new Building(filename: 'road_right.png', height: 39, price: 1).save(failOnError: true)
        def grass = new Building(filename: 'grass.png', height: 39, price: 1).save(failOnError: true)

        terrainService.generateGrass()

    }
    def destroy = {
    }
}
