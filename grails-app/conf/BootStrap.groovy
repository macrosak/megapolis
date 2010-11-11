import com.megapolis.game.Field
import com.megapolis.game.Building

class BootStrap {

    def init = { servletContext ->
        def small = new Building(filename: 'small.png', height: 191).save(failOnError: true)
        def medium = new Building(filename: 'medium.png', height: 256).save(failOnError: true)
        def high = new Building(filename: 'high.png', height: 344).save(failOnError: true)
        def rl = new Building(filename: 'road_left.png', height: 39).save(failOnError: true)
        def rr = new Building(filename: 'road_right.png', height: 39).save(failOnError: true)

        new Field(xCoord: 0, yCoord: 0, building: small).save(failOnError: true)
        new Field(xCoord: 1, yCoord: 0, building: high).save(failOnError: true)
    }
    def destroy = {
    }
}
