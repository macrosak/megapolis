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
