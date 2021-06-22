import actions.Action

class Head(
    override val name: String,
    override val description: String,
    override val totalHealth: Int,
    val totalAP: Int,
    val actions: List<Action> = listOf()
) : Part{
    override var health: Int = totalHealth
    var ap = totalAP
}