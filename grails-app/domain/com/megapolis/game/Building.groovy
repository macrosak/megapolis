package com.megapolis.game

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
        return type.currentProfit(field)
    }

    int withdraw() {
        return type.withdraw(field, lastWithdrawal)
    }
}
