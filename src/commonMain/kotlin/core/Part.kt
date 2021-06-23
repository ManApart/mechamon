package core

import core.actions.Action

interface Part {
    val name: String
    val description: String
    val totalHealth: Int
    val action: Action
    var health: Int
}