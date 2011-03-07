package com.megapolis.game

import com.megapolis.game.player.Player

class Building {

    int residents = 0
    Calendar lastWithdrawal
    BuildingType type

    static belongsTo = [field: Field]

    static constraints = {
        lastWithdrawal(nullable: true)
    }

    static namedQueries = {
        byPlayer { player ->
            field {
                owner {
                    eq('id', player.id)
                }
            }
        }
    }

    void init() {
        type.init(this)
    }

    int currentProfit() {
        return type.currentProfit(this)
    }

    boolean withdraw() {
        return type.withdraw(this)
    }

    Player getOwner() {
        return field.owner
    }

    void setOwner(Player newOwner) {
        field.owner = newOwner
    }
}
