package com.megapolis.game

import com.megapolis.game.player.Player

class Field {
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


    static createField(int x, int y)
    {
        double theta = Math.PI/4
        def R = [Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta)] as double[]

        double rx = R[0] * x + R[1] * y
        double ry = R[2] * x + R[3] * y

        return new Field(coordX: x, coordY: y, rx: rx, ry: ry/*, building: background*/).save()

    }
}
