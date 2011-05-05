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

class Residential extends BuildingType {
    private static final int RENT_PER_RESIDENT = 10

    def cityService

    int maxResidents

    static transients = ['cityService']

    void init(Building building) {
        building.residents = Math.round(0.1 * maxResidents)
    }

    int currentProfit(Building building) {
        // TODO residents based on lucrativity
        return building.residents * RENT_PER_RESIDENT * cityService.lucrativity(building.field)
    }

    long withdraw(Building building) {
        def profit = super.withdraw(building)
        // TODO residents based on lucrativity and lastWithdrawal
        if(profit >= 0) {
            if(building.residents < maxResidents) {
                Building.withTransaction {
                    building.residents = Math.min(maxResidents, (int) (building.residents + 0.1 * maxResidents))
                    building.save(flus: true)
                }
            }
        }
        return profit
    }

}
