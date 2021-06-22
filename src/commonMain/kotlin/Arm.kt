import actions.Action

class Arm(
    override val name: String,
    override val description: String,
    override val totalHealth: Int,
    val isRightArm: Boolean = true,
    val actions: List<Action> = listOf()
) : Part{
    override var health: Int = totalHealth
}