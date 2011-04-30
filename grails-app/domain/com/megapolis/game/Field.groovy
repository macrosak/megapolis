package com.megapolis.game

import com.megapolis.game.player.Player

class Field {
    private static final double theta = Math.PI/4
    private static final def R = [Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta)] as double[]

    int coordX
    int coordY
    double rx
    double ry

    Building building
    Player owner
    
    static constraints = {
        building(nullable: true)
        owner(nullable: true)
        coordX(unique: 'coordY')
    }

    int price() {
        return 200;
    }


    static Field createField(int x, int y) {

        double rx = getRx(x, y)
        double ry = getRy(x, y)

        return new Field(coordX: x, coordY: y, rx: rx, ry: ry)
    }

    static double getRx(x, y) {
        return R[0] * x + R[1] * y
    }

    static double getRy(x, y) {
        return R[2] * x + R[3] * y
    }
}
