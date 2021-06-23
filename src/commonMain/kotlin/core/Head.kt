package core

import core.actions.Action
import data.NOTHING

class Head(
    override val name: String,
    override val description: String,
    override val totalHealth: Int,
    val totalAP: Int,
    override val action: Action = NOTHING
) : Part {
    override var health: Int = totalHealth
    var ap = totalAP
}