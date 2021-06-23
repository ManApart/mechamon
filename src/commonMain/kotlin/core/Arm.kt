package core

import core.actions.Action
import data.NOTHING

class Arm(
    override val name: String,
    override val description: String,
    override val totalHealth: Int,
    val isRightArm: Boolean = true,
    override val action: Action = NOTHING
) : Part {
    override var health: Int = totalHealth
}